# AGENTS.md — PaginaTCG

## Propósito y alcance

- Este repositorio desarrolla una biblioteca web de cartas de Digimon Card Game.
- `PaginaTCG` es el nombre técnico provisional. No inventes todavía un nombre público para la aplicación.
- La estructura principal es `backend/` y `frontend/`. El backend usa el paquete `com.jotilla.paginatcg`.
- Antes de proponer o realizar cambios, consulta `README.md`, `docs/backend/modelo-datos.md` y las migraciones existentes que afecten a la tarea. El código y las migraciones implementadas prevalecen si una descripción de estado se ha quedado antigua.
- Mantén este archivo centrado en reglas duraderas. Actualízalo cuando el usuario corrija una suposición recurrente o adopte una nueva decisión permanente del proyecto.

## Forma de colaboración

- El proyecto también es un ejercicio de aprendizaje. Avanza en pasos pequeños y explica el motivo de cada decisión con lenguaje claro.
- En tareas didácticas, entrega únicamente el siguiente paso práctico y espera el resultado del usuario. No anticipes una cadena larga de pasos ni proporciones de entrada una solución completa que le quite la práctica.
- Formula preguntas de comprobación cuando ayuden al usuario a razonar sobre Java, Spring, Angular, SQL, JPA o el dominio.
- Cuando el usuario pida expresamente implementar, corregir o revisar algo, respeta exactamente ese alcance, inspecciona primero el estado real y verifica el resultado.
- No modifiques archivos ajenos al encargo. Conserva los cambios no relacionados que ya existan en el árbol de trabajo.
- No hagas `commit` ni `push` salvo petición expresa. Al completar un hito importante, recuerda al usuario que es un buen momento para revisar los cambios y guardar el progreso con Git.
- No presentes como implementadas decisiones que solo estén previstas.
- Escribe documentación y comentarios en español correcto y UTF-8, con sus tildes. Conserva sin traducir los identificadores Java/SQL, las rutas, los nombres oficiales de cartas y el texto oficial en inglés.

## Estado técnico de referencia

- Repositorio: `https://github.com/JotillaFT/PaginaTCG.git`.
- Backend confirmado: Spring Boot 4.1.1, Java 25, Gradle 9.5.1 y MySQL 8.4 en Docker.
- Puertos actuales: backend `8080` y MySQL del host `3307`.
- Flyway y Hibernate deben validar el esquema; la aplicación no debe modificarlo automáticamente. Mantén el modo de Hibernate equivalente a `validate`.
- La base implementada, aplicada y validada actualmente llega de V1 a V9. `V9__crear_impresiones_y_lanzamientos.sql` ya está aplicada y no debe modificarse, ni siquiera en formato, espacios, comentarios o salto de línea final. Las migraciones anteriores también son inmutables. Para cambiar el modelo, crea una migración nueva desde V10 o desde el siguiente número disponible tras verificar el directorio real.
- En Windows, los tests del backend han funcionado usando `GRADLE_USER_HOME=C:\GradleHome`.
- No trates los avisos de conversión entre LF y CRLF como errores funcionales. Aun así, `git diff --check` debe terminar sin errores reales de espacios o marcadores.

## Reglas de persistencia y Flyway

- MySQL y las migraciones Flyway son la fuente de verdad del esquema.
- Las entidades JPA deben reflejar exactamente las tablas, claves, nulabilidad, longitudes y restricciones creadas por Flyway.
- No uses `ddl-auto=update`, `create` ni `create-drop` para compensar una migración ausente.
- No cambies una migración que pueda haberse aplicado ya. Añade una migración incremental y conserva el historial reproducible.
- Antes de tocar entidades o migraciones, compara ambos lados del mapeo. Después, ejecuta tests y comprueba que Flyway e Hibernate validan el arranque cuando la tarea lo requiera.
- Representa la ausencia real de nivel, DP o costes mediante `NULL`; no uses `0`, `-1` ni `"-"` como sustitutos. Existen Digimon sin nivel.
- No inventes datos omitidos por una fuente externa. Registra o corrige esas excepciones de forma explícita en el importador o en los datos de origen controlados por el proyecto.

## Contrato del dominio Digimon TCG

### Carta y secciones

- `Carta` representa la identidad funcional única por código oficial. Su `id` es interno de MySQL y `codigo` es el número visible, por ejemplo `BT5-086`.
- `SeccionCarta` representa una parte funcional de la carta. Una carta normal suele tener una sección; una carta DUAL puede tener varias.
- Los datos asociados a un bloque pertenecen a su sección y no deben atribuirse automáticamente a todas las secciones de una `Carta`.
- `limiteCopiasRegla` representa el límite propio de construcción impreso o asociado a la carta, habitualmente 4. No representa automáticamente la lista externa de restricciones, baneos ni pares prohibidos; eso se modelará por separado.

### Impresiones, idiomas, lanzamientos e ilustradores

- `ImpresionCarta` representa cada variante física o gráfica de una `Carta`; no duplica su identidad funcional, secciones, efectos ni requisitos.
- La combinación `carta + idioma + numeroVariante` es única. `numeroVariante = 0` representa la impresión base dentro de un idioma y los valores superiores distinguen artes alternativas o reimpresiones.
- `Idioma` es un catálogo extensible con `EN`, `JA`, `KO` y `ZH_HANS`. El contenido funcional canónico y buscable permanece en inglés; inicialmente solo se importarán impresiones inglesas.
- Las impresiones de otros idiomas deben relacionarse con la misma `Carta`, sin crear traducciones o duplicados del modelo funcional.
- `Lanzamiento` representa productos, promociones, torneos u otras publicaciones y su fecha puede ser `null`. Una impresión puede relacionarse con varios lanzamientos.
- `Ilustrador` cataloga el nombre de crédito. Una impresión puede tener cero, uno o varios ilustradores y `ImpresionCartaIlustrador.orden` conserva el orden oficial del crédito.
- Heroicc no documenta actualmente el ilustrador como campo; su importación necesitará otra fuente o una extracción posterior desde la imagen.
- El frontend podrá mostrar solo la impresión base o todas las variantes. Las futuras colecciones y mazos podrán señalar la `ImpresionCarta` concreta que posee o utiliza el usuario.

### Forma, atributo, rasgos y colores

- Forma, atributo y rasgos son conceptos distintos: forma `Mega`, atributo `Vaccine` y rasgos como `Holy Warrior` o `Royal Knight`.
- `FormaCarta` es un catálogo extensible, no un enum. `SeccionCarta.formaCarta` representa la forma propia nullable de la sección y `RequisitoEvolucionNormal.formaOrigen` representa la forma de origen nullable exigida; son relaciones distintas con el mismo catálogo.
- No describas `Rasgo` genéricamente como “tipo”, porque se puede confundir con la categoría de carta.
- Los colores forman un catálogo relacional reutilizable. Una sección puede tener uno o varios colores y `orden` conserva el orden oficial.
- No sustituyas las relaciones de colores por columnas `color1`, `color2` ni por texto separado por comas.

### Texto oficial, etiquetas y palabras clave

- `BloqueTexto` conserva completa una caja oficial de texto en inglés. `contenidoOficial` es la fuente de verdad y el bloque no se divide inicialmente en cada efecto individual.
- `CategoriaBloqueTexto.EFFECT` representa la caja normal de efectos. No la llames `MAIN`: `[Main]` es una etiqueta oficial que puede aparecer dentro de esa caja.
- `EtiquetaEfecto` contiene únicamente etiquetas oficiales de activación o temporización, como `[Main]`, `[Delay]`, `[On Play]`, `[When Digivolving]` o `[When Attacking]`.
- `BloqueTextoEtiqueta` solo indica que una etiqueta aparece al menos una vez en el bloque completo. No representa una frase individual, su posición ni el número de apariciones.
- `PalabraClave` es el catálogo extensible de palabras clave oficiales, como `<Blocker>`, `<Rush>` o `<Jamming>`.
- `BloqueTextoPalabraClave` relaciona un bloque con una palabra clave presentada directamente como efecto propio de esa sección. No globalices esa relación a toda la carta.
- Una palabra clave meramente mencionada, concedida mediante otra frase —incluso a la propia carta—, retirada, negada o usada como condición no crea esa relación. Esos casos se consultan en `contenidoOficial`.
- No infieras ni persistas por ahora clasificaciones semánticas como `PLAY_COST_REDUCTION`, `DIGIVOLUTION_COST_REDUCTION`, `PLAY_WITHOUT_PAYING_COST`, `DIGIVOLVE_WITHOUT_PAYING_COST` o `IGNORE_DIGIVOLUTION_REQUIREMENTS`. Conserva sus distintas redacciones y contextos en `contenidoOficial` y usa búsqueda textual cuando corresponda.

### Evoluciones y fuentes externas

- Los requisitos normales de evolución están estructurados desde V8. Cada fila de `RequisitoEvolucionNormal` pertenece a una sección y representa una alternativa oficial completa cuyos campos forman conjuntamente una condición; `orden` conserva su posición visual.
- `categoriaOrigen` solo se rellena cuando Bandai exige explícitamente una categoría, como `TAMER`; no deduzcas `DIGIMON` o `DIGI_EGG` a partir de `nivelOrigen`. Tanto `nivelOrigen` como `formaOrigen` pueden ser `null` y el coste es obligatorio, incluido el valor `0`.
- Los colores concretos de un requisito se guardan mediante `RequisitoEvolucionNormalColor` y son distintos de los colores propios de la sección almacenados en `SeccionCartaColor`.
- `cualquierColor = true` significa que el requisito admite cualquier color y no debe tener relaciones con colores concretos. `cualquierColor = false` sin colores relacionados significa que el requisito no exige color.
- Las evoluciones por nombre, rasgo, ADN, Burst, Blast, App Fusion u otras condiciones escritas se conservan completas en `BloqueTexto.contenidoOficial` y se localizan mediante búsqueda textual o patrones oficiales exactos. No las conviertas en requisitos normales estructurados ni en etiquetas semánticas inferidas.
- Los datos importados desde Heroicc pueden contener omisiones. La falta de un color no significa “cualquier color”.
- “Cualquier color” se representa explícitamente con el booleano `cualquierColor`, no relacionando todos los colores existentes.
- Se conocen al menos las omisiones del color amarillo en los requisitos normales de evolución de `BT23-034 Sakuyamon` y `BT23-028 Coordemon`. La validación y corrección pertenece al futuro importador, no a las entidades actuales.

## Documentación y coherencia

- `README.md` resume el estado ejecutable, la estructura, las migraciones implementadas y el roadmap.
- `docs/backend/modelo-datos.md` explica el modelo, las relaciones y las decisiones de dominio con más detalle.
- Cuando cambie el esquema o se complete una migración, actualiza en el mismo trabajo la documentación afectada y este “Estado técnico de referencia” si queda desfasado.
- Los diagramas y ejemplos deben describir únicamente tablas y relaciones que existan realmente. Distingue siempre entre estado actual y siguiente paso previsto.
- Los JavaDoc explican la persistencia, las relaciones y el uso para consultas; no deben intentar funcionar como un motor completo de rulings.
- Usa terminología inequívoca: “artes alternativas”, “sección”, “categoría”, “relación”, “activación”, “catálogo”, “búsqueda” y demás ortografía española correcta.

## Verificación antes de entregar

- Revisa `git status --short` antes y después para identificar exactamente los archivos del encargo.
- Examina el diff y confirma que no se alteraron migraciones aplicadas ni archivos fuera de alcance.
- Para cambios del backend en Windows PowerShell, ejecuta preferentemente:

```powershell
$env:GRADLE_USER_HOME = 'C:\GradleHome'
Set-Location backend
.\gradlew.bat test
```

- Ejecuta `git diff --check` desde la raíz para comprobar los cambios no preparados.
- Ejecuta `git diff --cached --check` desde la raíz para comprobar los cambios preparados.
- Si solo se pidió documentación o comentarios, verifica además que no cambió el comportamiento del código ni el SQL.
- Informa al terminar de los archivos modificados, las decisiones relevantes y el resultado de las comprobaciones. No ocultes pruebas no ejecutadas ni fallos.
