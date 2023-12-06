# eclipselink-detach-bug
Minimal project to reproduce a bug in Eclipselink in which detach ends up loading lazy collections

Run with ./gradlew run

The output is:

```
[EL Info]: 2023-12-06 08:02:59.36--ServerSession(232307208)--EclipseLink, version: Eclipse Persistence Services - 4.0.2.v202306161219
Loaded state s1
Is collection initialized? false
Before detach
Loaded state s1
Loaded city s1b
Loaded city s1a
State s1 detached
Is collection initialized? true
```

Note that the code just does this:

```java
var state = em.find(State.class, "s1");
var cities = (IndirectSet<City>) state.getCities();
System.out.printf("Is collection initialized? %s%n", cities.isInstantiated());
System.out.println("Before detach");
em.detach(state);
System.out.printf("State %s detached%n", state.getCode());
System.out.printf("Is collection initialized? %s%n", cities.isInstantiated());
```

And the `cities` collection gets loaded during the detach operation.