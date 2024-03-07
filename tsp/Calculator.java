public class Calculator {
    public static double takenClasses;
    public static double futureClasses;
    public static double totalClasses;
    public static String[] grades;
    public static double[] gpa;

    public static void main(String[] args) {
        System.out.println("How many classes have you already taken?");
        takenClasses = StdIn.readDouble();

        System.out.println("How many classes are you taking this semester? "
                                   + "\n(Enter 0 if you do not wish to calculate your possible GPA after this semester)");
        futureClasses = StdIn.readDouble();

        totalClasses = takenClasses + futureClasses;

        grades = new String[(int) totalClasses];
        System.out.println("Enter your obtained and estimated grades for all your classes");
        for (int i = 0; i < totalClasses; i++) {
            System.out.print("Grade for class " + (i + 1) + ": ");
            grades[i] = StdIn.readString();
        }

        Calculator calculator = new Calculator();
        calculator.calculateGPA();
    }

    private double[] convertGrades(String[] x) {
        gpa = new double[x.length];

        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case "A":
                    gpa[i] = 4.0;
                    break;
                case "A-":
                    gpa[i] = 3.7;
                    break;
                case "B+":
                    gpa[i] = 3.3;
                    break;
                case "B":
                    gpa[i] = 3.0;
                    break;
                case "B-":
                    gpa[i] = 2.7;
                    break;
                case "C+":
                    gpa[i] = 2.3;
                    break;
                case "C":
                    gpa[i] = 2.0;
                    break;
                case "C-":
                    gpa[i] = 1.7;
                    break;
                case "D":
                    gpa[i] = 1.0;
                    break;
                case "F":
                    gpa[i] = 0;
                    break;
                default:
                    System.out.println("Invalid grade: " + x[i]);
                    break;
            }
        }
        return gpa;
    }

    private void calculateGPA() {
        double[] convertedGrades = convertGrades(grades);
        double totalGPA = 0.0;

        for (int i = 0; i < convertedGrades.length; i++) {
            totalGPA += convertedGrades[i];
        }

        double currentGPA = totalGPA / totalClasses;
        System.out.println("Your current GPA is: " + currentGPA);

        if (futureClasses > 0) {
            System.out.println("Enter your estimated grades for future classes");
            String[] futureGrades = new String[(int) futureClasses];

            for (int i = 0; i < futureGrades.length; i++) {
                System.out.print("Estimated grade for future class " + (i + 1) + ": ");
                futureGrades[i] = StdIn.readString();
            }

            double[] futureConvertedGrades = convertGrades(futureGrades);
            double futureTotalGPA = 0.0;

            for (int i = 0; i < futureConvertedGrades.length; i++) {
                futureTotalGPA += futureConvertedGrades[i];
            }

            double projectedGPA = (totalGPA + futureTotalGPA) / (totalClasses + futureClasses);
            System.out.println("Your projected GPA after this semester is: " + projectedGPA);
        }
    }
}
