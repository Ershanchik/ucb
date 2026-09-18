# Assignment 2 — Factory Method & Abstract Factory

**Theme: University Course Builder** (own theme, not from the lecture examples)

A small console application for a university that puts courses into its catalogue.

- **Part A (Factory Method)** — the system creates **one product**: a `Course`. Different
  departments/creators decide *which kind* of course is produced (lecture, lab, seminar).
- **Part B (Abstract Factory)** — the system creates a **family of products** that must match:
  `Syllabus` + `Assessment` + `ScheduleSlot`. All three must belong to the same delivery mode
  (ONLINE or ON-CAMPUS). An online auto-graded quiz must never end up scheduled in a physical
  exam hall.

---

## 1. Repository structure

```
assignment2-design-patterns/
├── src/
│   ├── factorymethod/                  (Part A)
│   │   ├── Course.java                 Product interface
│   │   ├── LectureCourse.java          ConcreteProduct 1
│   │   ├── LabCourse.java              ConcreteProduct 2
│   │   ├── SeminarCourse.java          ConcreteProduct 3
│   │   ├── CourseCreator.java          Creator: factory method + business method
│   │   ├── LectureCourseCreator.java   ConcreteCreator 1
│   │   ├── LabCourseCreator.java       ConcreteCreator 2
│   │   ├── SeminarCourseCreator.java   ConcreteCreator 3
│   │   └── FactoryMethodDemo.java      Client (main)
│   └── abstractfactory/                (Part B)
│       ├── Syllabus.java               Abstract product 1
│       ├── Assessment.java             Abstract product 2
│       ├── ScheduleSlot.java           Abstract product 3
│       ├── OnlineSyllabus.java         Online family
│       ├── OnlineAssessment.java
│       ├── OnlineScheduleSlot.java
│       ├── OnCampusSyllabus.java       On-campus family
│       ├── OnCampusAssessment.java
│       ├── OnCampusScheduleSlot.java
│       ├── CoursePackageFactory.java   AbstractFactory interface
│       ├── OnlineCourseFactory.java    ConcreteFactory 1
│       ├── OnCampusCourseFactory.java  ConcreteFactory 2
│       ├── CourseBuilder.java          Client (factory injected by composition)
│       └── AbstractFactoryDemo.java    main: the single point where the family is chosen
├── README.md
└── .gitignore
```

## 2. How to run

Java 17+ (text blocks and switch expressions are used). Tested on OpenJDK 21.

```bash
javac -d out $(find src -name '*.java')

# Part A
java -cp out factorymethod.FactoryMethodDemo

# Part B — the whole family switches with one argument
java -cp out abstractfactory.AbstractFactoryDemo online
java -cp out abstractfactory.AbstractFactoryDemo campus
```

## 3. Part A — Factory Method

| Role in the pattern | Class in this project |
|---|---|
| Product | `Course` (`title()`, `credits()`, `weeklyPlan()`) |
| ConcreteProduct | `LectureCourse`, `LabCourse`, `SeminarCourse` |
| Creator | `CourseCreator` — abstract `createCourse(String)` + business method `openCourse(String)` |
| ConcreteCreator | `LectureCourseCreator`, `LabCourseCreator`, `SeminarCourseCreator` |
| Client | `FactoryMethodDemo` |

`CourseCreator.openCourse()` contains the workflow (build the catalogue entry, compute the
student workload from the credits) and calls the product only through the `Course` interface.
It never mentions a concrete class. The choice of concrete course lives in the subclass —
i.e. the pattern is built on **inheritance**.

The client keeps a `List<CourseCreator>` and calls `openCourse(...)`; there is no
`new LectureCourse(...)` anywhere in the client.

**Adding a fourth course type** (e.g. `OnlineWorkshopCourse`): add one product class and one
creator subclass. `CourseCreator`, `openCourse()` and the other creators stay untouched →
Open/Closed Principle.

## 4. Part B — Abstract Factory

| Role in the pattern | Class in this project |
|---|---|
| AbstractProduct | `Syllabus`, `Assessment`, `ScheduleSlot` |
| ConcreteProduct | `Online*` family and `OnCampus*` family |
| AbstractFactory | `CoursePackageFactory` (one create-method per product) |
| ConcreteFactory | `OnlineCourseFactory`, `OnCampusCourseFactory` |
| Client | `CourseBuilder` — gets the factory through its **constructor** |

`CourseBuilder.assemble()` asks the injected factory for the three products and formats the
package. It uses only the three interfaces, so it cannot mix families even by accident.
The delivery mode is selected in exactly one place: `AbstractFactoryDemo.pickFactory()` —
the only method in the program that mentions `OnlineCourseFactory` / `OnCampusCourseFactory`.

**Why this is Abstract Factory and not just three Factory Methods:** each concrete factory
produces **three products at once and guarantees they are compatible**. Three independent
factory methods would let a caller combine an `OnlineAssessment` with an
`OnCampusScheduleSlot`; here that combination is unreachable, and switching the entire family
is one line. Factory Method also relies on inheritance (subclass overrides a method), while
Abstract Factory relies on **composition** (the client holds a factory object).

## 5. SOLID

- **OCP** — a new delivery mode (e.g. HYBRID) means one new factory + three new product
  classes and one new `case` in `pickFactory()`. `CourseBuilder` and every existing family are
  not modified.
- **SRP** — products describe course content; factories only know how to produce a consistent
  set; `CourseBuilder` only assembles and formats. Creation logic and business logic are
  separated.
- **DIP** — clients depend on `Course`, `Syllabus`, `Assessment`, `ScheduleSlot`,
  `CoursePackageFactory`, never on concrete classes.
- **LSP** — any `CourseCreator` subclass can replace another in the client's list; any factory
  can be passed to `CourseBuilder`.

## 6. Drawbacks / when this is over-engineering

- The class count grows fast: two families × three products + two factories + interfaces.
  For a program with a single course format this is pure overhead — a constructor would do.
- **Weak spot of Abstract Factory:** adding a **new kind of product** to the family (say
  `Textbook`) forces a change in the `CoursePackageFactory` interface and in *every* concrete
  factory. The pattern is open for new families, closed for new product kinds.
- `pickFactory()` still contains a `switch`, but it is deliberately isolated in one method:
  the ladder no longer spreads through the business logic.
