import java.util.HashMap;
import java.util.Scanner;


class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}


class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}


class Course {
    private String name;
    private String prerequisite;
    private int maxEnrollment;
    private int enrolledStudents;

    public Course(String name, String prerequisite, int maxEnrollment) {
        this.name = name;
        this.prerequisite = prerequisite;
        this.maxEnrollment = maxEnrollment;
        this.enrolledStudents = 0;
    }

    public String getName() {
        return name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public boolean isFull() {
        return enrolledStudents >= maxEnrollment;
    }

    public void enroll() throws CourseFullException {
        if (isFull()) {
            throw new CourseFullException("Error: CourseFullException - " + name + " is full.");
        }
        enrolledStudents++;
    }
}


public class UniversityEnrollment {
    private static HashMap<String, Boolean> completedCourses = new HashMap<>();
    private static HashMap<String, Course> courses = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        courses.put("Core Java", new Course("Core Java", null, 3));
        courses.put("Advanced Java", new Course("Advanced Java", "Core Java", 2));

        try {
            
            System.out.print("Enroll in Course: ");
            String courseName = scanner.nextLine();

            if (!courses.containsKey(courseName)) {
                System.out.println("Error: Course not found.");
                return;
            }

            Course course = courses.get(courseName);
            String prerequisite = course.getPrerequisite();

            
            if (prerequisite != null && !completedCourses.getOrDefault(prerequisite, false)) {
                throw new PrerequisiteNotMetException(
                    "Error: PrerequisiteNotMetException - Complete " + prerequisite + " before enrolling in " + courseName + "."
                );
            }

            
            course.enroll();
            System.out.println("Successfully enrolled in " + courseName);

        
            completedCourses.put(courseName, true);

        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        } finally {
            scanner.close();
        }
    }
}
