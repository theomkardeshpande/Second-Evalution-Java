import java.util.ArrayList;
import java.util.List;

class Student {
    private int studentID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Student(int studentID, String name, String email, String phoneNumber, String address) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nEmail: " + email + "\nPhone Number: " +
                phoneNumber + "\nAddress: " + address;
    }
}

class Course {
    private int courseID;
    private String courseName;
    private String instructor;
    private int credits;
    private int maxCapacity;
    private List<Student> enrolledStudents;

    public Course(int courseID, String courseName, String instructor, int credits, int maxCapacity) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxCapacity;
    }

    public void enrollStudent(Student student) {
        if (!isFull()) {
            enrolledStudents.add(student);
        } else {
            System.out.println("Course is already full. Cannot enroll more students.");
        }
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + "\nCourse Name: " + courseName + "\nInstructor: " + instructor +
                "\nCredits: " + credits + "\nMax Capacity: " + maxCapacity +
                "\nEnrolled Students: " + enrolledStudents.size();
    }
}

class Enrollment {
    private int enrollmentID;
    private int studentID;
    private int courseID;
    private String enrollmentDate;

    public Enrollment(int enrollmentID, int studentID, int courseID, String enrollmentDate) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public String toString() {
        return "Enrollment ID: " + enrollmentID + "\nStudent ID: " + studentID + "\nCourse ID: " + courseID +
                "\nEnrollment Date: " + enrollmentDate;
    }
}

class CollegeManager {
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;

    public CollegeManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.enrollments = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudentInCourse(Student student, Course course, String enrollmentDate) {
        if (students.contains(student) && courses.contains(course)) {
            if (!course.isFull()) {
                Enrollment enrollment = new Enrollment(enrollments.size() + 1, student.getStudentID(),
                        course.getCourseID(), enrollmentDate);
                enrollments.add(enrollment);
                course.enrollStudent(student);
                System.out.println("Enrollment successful.");
            } else {
                System.out.println("Course is already full. Cannot enroll more students.");
            }
        } else {
            System.out.println("Student or course not found in the system.");
        }
    }

    public void displayStudentsInCourse(Course course) {
        if (courses.contains(course)) {
            List<Student> enrolledStudents = course.getEnrolledStudents();
            System.out.println("Students enrolled in course " + course.getCourseName() + ":");
            for (Student student : enrolledStudents) {
                System.out.println(student.getName());
            }
        } else {
            System.out.println("Course not found in the system.");
        }
    }

    public void displayCoursesForStudent(Student student) {
        if (students.contains(student)) {
            System.out.println("Courses enrolled by " + student.getName() + ":");
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getStudentID() == student.getStudentID()) {
                    Course course = getCourseById(enrollment.getCourseID());
                    System.out.println(course.getCourseName());
                }
            }
        } else {
            System.out.println("Student not found in the system.");
        }
    }

    private Course getCourseById(int courseID) {
        for (Course course : courses) {
            if (course.getCourseID() == courseID) {
                return course;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        Student student2 = new Student(2, "Jane Smith", "jane.smith@example.com", "987-654-3210", "456 Elm St");

        Course course1 = new Course(101, "Computer Science 101", "Dr. Smith", 3, 30);
        Course course2 = new Course(102, "Mathematics 101", "Dr. Johnson", 4, 25);

        CollegeManager collegeManager = new CollegeManager();

        collegeManager.addStudent(student1);
        collegeManager.addStudent(student2);

        collegeManager.addCourse(course1);
        collegeManager.addCourse(course2);

        collegeManager.enrollStudentInCourse(student1, course1, "2023-10-03");
        collegeManager.enrollStudentInCourse(student2, course1, "2023-10-04");
        collegeManager.enrollStudentInCourse(student1, course2, "2023-10-05");

        System.out.println("Student Information:\n" + student1);
        System.out.println("\nCourse Information:\n" + course1);
       
    }
}