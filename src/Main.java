public class Main {

    public static void main(String[] args) {

        Pipeline pipeline = new Pipeline();

        pipeline.controlBit = 0;

        while (pipeline.cycleCount < pipeline.instructionCache.length) {

            System.out.println("\n" + pipeline.cycleCount + " ----------------------------------------------------------------------");

            // IF     ------------------------------------------------------------------------------------------------------
            pipeline.createIfStage();


            // ID     ------------------------------------------------------------------------------------------------------
            if (pipeline.controlBit == 1) {
                pipeline.createIdReadStage();
            } else {
                pipeline.id_write = pipeline.id_read;
            }
            pipeline.id_write.printIdWrite();
/*
            // EX     ------------------------------------------------------------------------------------------------------
            if (pipeline.controlBit == 1) {
                pipeline.createExReadStage();
            } else {
                pipeline.createExWriteStage();
            }
            pipeline.ex_read.printExRead();
            pipeline.ex_write.printExWrite();

            // MEM     -----------------------------------------------------------------------------------------------------
            if (pipeline.controlBit == 1) {
                pipeline.createMemReadStage();
            } else {
                pipeline.createMemWriteStage();
                pipeline.mem_write = pipeline.mem_read;
            }
            pipeline.mem_read.printMemRead();
            pipeline.mem_write.printMemWrite();

            // WB READ----------------------------------------------------------------------------------------------------
            if (pipeline.controlBit == 1) {
                pipeline.createWbReadStage();

            } else {
                pipeline.createWbWriteStage();
                pipeline.wb_write = pipeline.wb_read;
            }
            pipeline.wb_write.printWBStage();
            /
 */

            System.out.println("\n" + pipeline.cycleCount + " ----------------------------------------------------------------------");

            // set the control bits to manage the pipeline
            if (pipeline.cycleCount == 1) {
                pipeline.controlBit = 1;
            } else if (pipeline.cycleCount % 2 == 0) {
                pipeline.controlBit = 0;
            } else {
                pipeline.controlBit = 1;
            }

            // increment all of the counters
            pipeline.cycleCount = pipeline.cycleCount + 1;
            pipeline.programCounter = pipeline.programCounter + 4;
        }
    }
}  // class end
