package elezioni;


public class CandidatoNonValido extends Exception {
  public CandidatoNonValido() {
    super();
  }
  public CandidatoNonValido(String msg) {
    super(msg);
  }
}
