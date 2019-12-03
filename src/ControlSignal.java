public class ControlSignal {

    private int aluSource;
    private int aluOperation;



    private int memoryRead;
    private int memoryWrite;
    private int branch;
    private int memoryToRegister;
    private int registerWrite;
    private int registerDestination;

    public int getAluSource() {
        return aluSource;
    }

    public int getAluOperation() {
        return aluOperation;
    }

    public int getRegisterWrite() {
        return registerWrite;
    }

    public int getMemoryRead() {
        return memoryRead;
    }

    public ControlSignal(Instruction instr) {

        if (instr.getCodeName().equals("add") || instr.getCodeName().equals("sub")) {
            registerDestination = 1; //
            aluSource = 0; //
            aluOperation = 10;
            memoryRead = 0;//
            memoryWrite = 0;//
            branch = 0;//
            memoryToRegister = 0;//
            registerWrite = 1;//
        } else if (instr.getCodeName().equals("lb")) {
            registerDestination = 0;
            aluSource = 0;
            aluOperation = 0;
            memoryRead = 0;
            memoryWrite = 0;
            branch = 0;
            memoryToRegister = 0;
            registerWrite = 0;
        } else if (instr.getCodeName().equals("sb")) {
            registerDestination = 0;
            aluSource = 0;
            aluOperation = 0;
            memoryRead = 0;
            memoryWrite = 0;
            branch = 0;
            memoryToRegister = 0;
            registerWrite = 0;
        } else if (instr.getCodeName().equals("nop")) {
            registerDestination = -1;
            aluSource = -1;
            aluOperation = -1;
            memoryRead = -1;
            memoryWrite = -1;
            branch = -1;
            memoryToRegister = -1;
            registerWrite = -1;
        }
    }

    public int getRegisterDestination() {
        return registerDestination;
    }

    public void printControlSignal() {
        System.out.println("\nregisterDestination  " + registerDestination);
        System.out.println("aluSource  " + aluSource);
        System.out.println("aluOperation  " + aluOperation);
        System.out.println("memoryRead  " + memoryRead);
        System.out.println("memoryWrite  " + memoryWrite);
        System.out.println("branch  " + branch);
        System.out.println("memoryToRegister  " + memoryToRegister);
        System.out.println("registerWrite  " + registerWrite + "\n");

    }
}



