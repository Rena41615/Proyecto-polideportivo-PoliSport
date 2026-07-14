# Guia de Contribucion — Estrategia de Branching (GitHub Flow para Microservicios)

Este documento formaliza como el equipo aplica **GitHub Flow** en un monorepo
de microservicios (11 modulos Maven independientes: `gateway-service` +
10 servicios de negocio). GitHub Flow es intencionalmente simple (una sola
rama larga, `main`), por lo que las reglas de este documento existen para
adaptarlo a un contexto donde varios servicios evolucionan en paralelo
dentro del mismo repositorio.

## 1. Rama principal

- **`main`** es la unica rama larga del proyecto. Debe estar **siempre
  desplegable**: todo commit en `main` tiene que compilar, pasar los tests
  y cumplir el minimo de cobertura JaCoCo (80% en el paquete `/service` de
  cada microservicio afectado).
- Nadie hace push directo a `main`. Todo cambio entra via **Pull Request**.
- `main` debe estar protegida en la configuracion del repositorio
  (Settings → Branches → Branch protection rules):
  - Require pull request before merging (minimo 1 approval).
  - Require status checks to pass before merging (el workflow de CI,
    ver seccion 4).
  - No permitir force-push ni borrado de `main`.

## 2. Convencion de nombres de rama

Como el repositorio contiene varios microservicios, el nombre de la rama
**debe indicar que microservicio(s) toca** para que el equipo entienda el
alcance de un PR sin abrir el diff:

```
<tipo>/<microservicio>-<descripcion-corta>
```

| Tipo        | Uso                                              | Ejemplo                              |
| ----------- | ------------------------------------------------- | ------------------------------------- |
| `feature/`  | Nueva funcionalidad                                | `feature/atletas-endpoint-patch`      |
| `fix/`      | Correccion de un bug (no urgente en produccion)    | `fix/nutricion-validacion-dto`        |
| `hotfix/`   | Correccion urgente en produccion                   | `hotfix/iam-login-bug`                |
| `test/`     | Trabajo enfocado en tests/cobertura                | `test/staff-jacoco-80`                |
| `chore/`    | Tareas de mantenimiento (deps, CI, docs)           | `chore/gateway-ci-pipeline`           |
| `docs/`     | Cambios de documentacion                           | `docs/readme-endpoints`               |

Cuando el cambio es transversal (por ejemplo, el `pom.xml` raiz o el
`gateway-service`), se usa el microservicio `root` o `gateway`:
`chore/root-jacoco-rules`, `feature/gateway-nueva-ruta`.

## 3. Regla de oro: un PR, un microservicio

Para mantener los Pull Requests pequeños, revisables y con builds de CI
rapidos, cada PR debe tocar **un solo microservicio** siempre que sea
posible (excepcion: cambios de infraestructura compartida como el
`pom.xml` raiz, `docker-compose.yml` o el `gateway-service`, que por
naturaleza son transversales).

Esto permite:

- Revisiones enfocadas (el reviewer solo necesita conocer el dominio de
  ese microservicio).
- CI mas rapido: el pipeline solo construye/testea el modulo modificado
  (ver seccion 4).
- Rollback simple: revertir un PR no arrastra cambios de otros servicios.

## 4. Integracion continua por microservicio

El workflow `.github/workflows/ci.yml` detecta, por cada Pull Request,
que carpetas de microservicio cambiaron y **solo construye y testea esos
modulos** (usando `mvn -pl <modulo> -am`), en vez de recompilar los 11
servicios en cada PR. Cada job de servicio ejecuta `mvn verify`, que
incluye la validacion JaCoCo (>= 80% de cobertura de linea en el paquete
`/service`) configurada en el `pom.xml` raiz. Si un servicio baja del 80%,
el check falla y el PR no puede fusionarse.

## 5. Commits

Se recomienda [Conventional Commits](https://www.conventionalcommits.org/)
con el microservicio como *scope*:

```
feat(atletas): agrega actualizacion parcial (PATCH)
fix(iam): corrige NPE al eliminar usuario inexistente
test(staff): cubre MiembrosRolStaffService al 100%
docs(gateway): documenta rutas del API Gateway
chore(root): agrega regla de cobertura JaCoCo
```

## 6. Flujo resumido

```
main ──── merge ──── merge ──── merge ────
          ↑           ↑           ↑
          |           |           |
feature/  fix/        test/       hotfix/
atletas-  nutricion-  staff-      iam-login
crud      validacion  jacoco-80   -bug
```

1. Crear rama desde `main` siguiendo la convencion de nombres.
2. Desarrollar y commitear en pequeños incrementos.
3. Abrir Pull Request hacia `main` (descripcion clara + microservicio(s)
   afectado(s)).
4. Esperar a que el CI (build + tests + JaCoCo) pase en verde.
5. Obtener al menos 1 aprobacion de otro integrante del equipo.
6. Merge a `main` (squash recomendado para mantener el historial limpio).
7. `main` queda desplegable; el despliegue a Render se dispara desde ahi.

## 7. Hotfixes

Para bugs en produccion, el flujo es identico pero con prioridad alta:
rama `hotfix/<servicio>-<descripcion>` desde `main`, PR con revision
express, y merge apenas el CI pasa. No se salta la regla de cobertura ni
la revision, incluso en hotfixes.
