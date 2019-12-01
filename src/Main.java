public class Main {

    public static void main(String[] args) {

        Pipeline pipeline = new Pipeline();

        int cycle = 0;
        int controlBit = 0;

        // remove instructions from the program counter, maybe implement them as a stack

        while (cycle < pipeline.instructionCache.length * 8) {

            System.out.println("\n" + cycle + " ----------------------------------------------------------------------");

            if(cycle > pipeline.instructionCache.length){
                pipeline.cycleCount = 0;
            }else {
                pipeline.cycleCount = pipeline.cycleCount + 1;
            }

            // IF     ------------------------------------------------------------------------------------------------------
            pipeline.if_stage.setIfStage(pipeline.getCycleCount(), pipeline);

            // ID     ------------------------------------------------------------------------------------------------------
            if (controlBit == 1) {
                pipeline.id_read.setIDStage(
                        pipeline.if_stage.getIfWrite_ProgramCounter(),
                        pipeline.if_stage.getIfWrite_Instruction(),
                        pipeline.getMemory());
            } else {
                pipeline.id_write = pipeline.id_read;
            }
            pipeline.id_write.printIdWrite();


            // EX     ------------------------------------------------------------------------------------------------------
            if (controlBit == 1) {
                pipeline.ex_read.setEXStage(
                        pipeline.id_read.getIdWrite_ControlSignal(),
                        pipeline.id_read.getIdWrite_ProgramCounter(),
                        pipeline.id_read.getIdWrite_ReadData1(),
                        pipeline.id_read.getIdWrite_ReadData2(),
                        pipeline.id_read.getIdWrite_SignExtendedOffset(),
                        pipeline.id_read.getIdWrite_WriteRegister_20_16(),
                        pipeline.id_read.getIdWrite_WriteRegister_15_11(),
                        pipeline.id_read.getIdWrite_RFormatFunc());
            } else {
                pipeline.ex_write = pipeline.ex_read;
            }
            pipeline.ex_read.printExRead();
            pipeline.ex_write.printExWrite();


            // MEM     -----------------------------------------------------------------------------------------------------
            if (controlBit == 1) {
                pipeline.mem_read.setMEMStage(
                        pipeline.ex_read.getExWrite_AluZero(),
                        pipeline.ex_read.getExWrite_AluResult(),
                        pipeline.ex_read.getExWrite_SwValue(),
                        pipeline.ex_read.getExWrite_WriteRegNum(),
                        pipeline.ex_read.getExWrite_ControlSignal());
            } else {
                pipeline.mem_write = pipeline.mem_read;
            }
            pipeline.mem_read.printMemRead();
            pipeline.mem_write.printMemWrite();

            // WB READ----------------------------------------------------------------------------------------------------


            if (controlBit == 1) {
                pipeline.wb_read.setWBStage(
                        pipeline.mem_read.getMemWrite_ControlSignal(),
                        pipeline.mem_read.getMemWrite_LWDataValue(),
                        pipeline.mem_read.getMemWrite_AluResult(),
                        pipeline.mem_read.getMemWrite_WriteRegNum(),
                        pipeline.getMemory());
            } else {
                pipeline.wb_write = pipeline.wb_read;
            }
            pipeline.wb_write.printWBStage();

            // set the control bits to manage the pipeline
            if (cycle == 1) {
                controlBit = 1;
            } else if (cycle % 2 == 0) {
                controlBit = 0;
            } else {
                controlBit = 1;
            }

            System.out.println("\n" + cycle + " ----------------------------------------------------------------------");

            cycle = cycle + 1;

        }
    }

}  // class end
