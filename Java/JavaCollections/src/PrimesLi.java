import java.util.Iterator;

public class PrimesLi implements Iterable<PrimesGenerator> {
	public PrimesGenerator pr;
	public Iterator iterator()
	{
		return pr;
	}
	public PrimesLi(PrimesGenerator pr2)
	{
		pr= pr2;
	}
}
