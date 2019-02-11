Provider
========
Service API / Data API for the UI.

### Building

```console
Michaels-Air:provider grudkowm$ mvn spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building monitor-provider 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] >>> spring-boot-maven-plugin:2.0.0.BUILD-SNAPSHOT:run (default-cli) > test-compile @ monitor-provider >>>
[INFO]
[INFO] --- maven-resources-plugin:3.0.1:resources (default-resources) @ monitor-provider ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] Copying 10 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ monitor-provider ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 46 source files to /private/var/tmp/order-entry-dashboard/provider/target/classes
[INFO] /private/var/tmp/order-entry-dashboard/provider/src/main/java/com/notatracer/sandbox/app/websocket/web/websocket/configuration/WebSocketConfig.java: /private/var/tmp/order-entry-dashboard/provider/src/main/java/com/notatracer/sandbox/app/websocket/web/websocket/configuration/WebSocketConfig.java uses or overrides a deprecated API.
[INFO] /private/var/tmp/order-entry-dashboard/provider/src/main/java/com/notatracer/sandbox/app/websocket/web/websocket/configuration/WebSocketConfig.java: Recompile with -Xlint:deprecation for details.
[INFO] /private/var/tmp/order-entry-dashboard/provider/src/main/java/com/notatracer/sandbox/app/websocket/integration/emulation/OrderFeedStreamGenerator.java: Some input files use unchecked or unsafe operations.
[INFO] /private/var/tmp/order-entry-dashboard/provider/src/main/java/com/notatracer/sandbox/app/websocket/integration/emulation/OrderFeedStreamGenerator.java: Recompile with -Xlint:unchecked for details.
[INFO]
[INFO] --- maven-resources-plugin:3.0.1:testResources (default-testResources) @ monitor-provider ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /private/var/tmp/order-entry-dashboard/provider/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ monitor-provider ---
[INFO] No sources to compile
[INFO]
[INFO] <<< spring-boot-maven-plugin:2.0.0.BUILD-SNAPSHOT:run (default-cli) < test-compile @ monitor-provider <<<
[INFO]
[INFO] --- spring-boot-maven-plugin:2.0.0.BUILD-SNAPSHOT:run (default-cli) @ monitor-provider ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)

2019-02-10 19:37:55.177  INFO 10923 --- [           main] c.n.sandbox.app.websocket.WebsocketApp   : Starting WebsocketApp on Michaels-Air.fios-router.home with PID 10923 (/private/var/tmp/order-entry-dashboard/provider/target/classes started by grudkowm in /private/var/tmp/order-entry-dashboard/provider)
2019-02-10 19:37:55.182  INFO 10923 --- [           main] c.n.sandbox.app.websocket.WebsocketApp   : No active profile set, falling back to default profiles: default
2019-02-10 19:37:55.266  INFO 10923 --- [           main] ConfigServletWebServerApplicationContext : Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@5c7d3787: startup date [Sun Feb 10 19:37:55 EST 2019]; root of context hi
```

