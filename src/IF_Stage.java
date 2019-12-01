public class IF_Stage {
    /*  IF Stage
            You will fetch the next instruction out of the Instruction Cache.
            Put it in the WRITE version of the IF/ID pipeline register.
             */
    int controlBit = 0;
    public void setControlBit(int controlBit){
        this.controlBit = controlBit;
    }
    public int getControlBit() {
        return controlBit;
    }

    public int getIfWrite_ProgramCounter() {
        return ifWrite_ProgramCounter;
    }

    public int getIfWrite_Instruction() {
        return ifWrite_Instruction;
    }

    // IF WRITE
    int ifWrite_ProgramCounter; // IF start
    int ifWrite_Instruction; // IF start


    public IF_Stage(){};

    public void setIfStage(int ifRead_ProgramCounter, Pipeline pipeline){
        this.ifWrite_ProgramCounter = ifRead_ProgramCounter;
        this.ifWrite_Instruction = pipeline.instructionCache[ifWrite_ProgramCounter];
        System.out.println("\nIF Write\n" + Integer.toHexString(ifWrite_Instruction));
    }

    /*
            // IF     ------------------------------------------------------------------------------------------------------
          IF Stage
        You will fetch the next instruction out of the Instruction Cache.
        Put it in the WRITE version of the IF/ID pipeline register.

    pipeline.ifWrite_ProgramCounter = pipeline.programCounter++;
    pipeline.ifWrite_Instruction = pipeline.instructionCache[pipeline.programCounter];
        System.out.println("\nIF Write\n" + Integer.toHexString(pipeline.ifWrite_Instruction));

     */

}
