package fr.esgi.taskmanager.infra.input;

public class ScriptChoice {
    public String[] args;
    public ScriptChoice(String[] args) {
        this.args = args;


    }
    public String choose() {
        if (args.length < 1) {

            // System.out.println("Error: No command specified.");
            // System.exit(1);
        }
        return args[0];
      /*  try {
            switch (args[0]) {
                case "list":
                    listTasks();
                    break;
                case "add":
                    addTask(args);
                    break;
                case "remove":
                    removeTask(args);
                    break;
                case "update":
                    updateTask(args);
                    break;
                default:
                    System.out.println("Error: Invalid command.");
                    System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to write to log file.");
            System.exit(1);
        }
    }*/
    }
}
