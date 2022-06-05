import task_enums.CompileEnum
import task_enums.TestEnum

name = '20214'
task1 = new Task(1, new Date(2022, 1, 1, 11, 11))
task1.setAutoDocumentable(false)
task1.setCompileState(CompileEnum.COMPILABLE)
task1.setTestingState(TestEnum.PASSED)
task1.setExecutable(true)

def attendance = [
        new Date(777, 7, 7)  : 0,
        new Date(2022, 3, 15): 1]

student1 = new Student(
        1,
        "Inikta",
        "Nikita Batenko Sergeevich",
        "https://github.com/Inikta/OOP.git",
        "main")
student1.setStudentStats(new StudentStats([task1: 1] as Map<Task, Integer>, attendance))

studentsGroup = new StudentsGroup(name, [student1])
