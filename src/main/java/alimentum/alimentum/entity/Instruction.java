package alimentum.alimentum.entity;



public class Instruction {
  private String instruction;

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  @Override
  public String toString() {
    return "Instruction{" +
                   "instruction='" + instruction + '\'' +
                   '}';
  }
}
