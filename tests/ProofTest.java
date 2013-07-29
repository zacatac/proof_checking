import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Zack
 * Date: 7/24/13
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProofTest {


	@Test
	public void testExtendProof() throws Exception {
//		proof #1 (works)
		Proof proof = new Proof();
		proof.extendProof("show (q=>q)");
		proof.extendProof("assume q");
		proof.extendProof("ic 2 (q=>q)");
		assertTrue(proof.isComplete());
		
		
//		proof #2 (works)
		proof = new Proof();
		proof.extendProof("show (p=>((p=>q)=>q))");
		proof.extendProof("assume p");
		proof.extendProof("show ((p=>q)=>q)");
		proof.extendProof("assume (p=>q)");
		proof.extendProof("show q");
		proof.extendProof("mp 2 3.1 q");
		proof.extendProof("ic 3.2 ((p=>q)=>q)");
		proof.extendProof("ic 3 (p=>((p=>q)=>q))");
		assertTrue(proof.isComplete());
		
//		proof #3 (works)
		proof = new Proof();
		proof.extendProof("show (~~p=>p)");
		proof.extendProof("assume ~~p");
		proof.extendProof("show p");
		proof.extendProof("assume ~p");
		proof.extendProof("co 2 3.1 p");
		proof.extendProof("ic 3 (~~p=>p)");
		assertTrue(proof.isComplete());
		
//		proof #4 (works)
		proof = new Proof();
		proof.extendProof("show ((a=>(b=>c))=>((a=>b)=>(a=>c)))");
		proof.extendProof("assume (a=>(b=>c))");
		proof.extendProof("show ((a=>b)=>(a=>c))");
		proof.extendProof("assume (a=>b)");
		proof.extendProof("show (a=>c)");
		proof.extendProof("assume a");
		proof.extendProof("mp 2 3.2.1 (b=>c)");
		proof.extendProof("mp 3.2.1 3.1 b");
		proof.extendProof("mp 3.2.3 3.2.2 c");
		proof.extendProof("ic 3.2.4 (a=>c)");
		proof.extendProof("ic 3.2 ((a=>b)=>(a=>c))");
		proof.extendProof("ic 3 ((a=>(b=>c))=>((a=>b)=>(a=>c)))");
		assertTrue(proof.isComplete());
		
//		proof #5 (works) 
		proof = new Proof();
		proof.extendProof("show ((a=>b)=>((b=>c)=>(a=>c)))");
		proof.extendProof("assume (a=>b)");
		proof.extendProof("show ((b=>c)=>(a=>c))");
		proof.extendProof("assume (b=>c)");
		proof.extendProof("show (a=>c)");
		proof.extendProof("assume a");
		proof.extendProof("show c");
		proof.extendProof("assume ~c");
		proof.extendProof("mt 3.2.2.1 3.1 ~b");
		proof.extendProof("mt 2 3.2.2.2 ~a");
		try{
			proof.extendProof("co 3.2.2.3 3.2.1"); // THIS MAY BE FAULTY JUST BECAUSE OF CO
			fail("Should have caught: wrong number of things");
		} catch (IllegalLineException e){
			
		}
		
		proof.extendProof("co 3.2.2.3 3.2.1 c");
		try{
			proof.extendProof("ic 3.2.2.4 (a=>c)");
			fail("Should have caught: Unable to refer to line 3.2.2.4");
		}catch (IllegalLineException e){
			
		}
		proof.extendProof("ic 3.2.2 (a=>c)");
		proof.extendProof("ic 3.2 ((b=>c)=>(a=>c))");
		proof.extendProof("ic 3 ((a=>b)=>((b=>c)=>(a=>c)))");
		assertTrue(proof.isComplete());
	
		
////		proof #6 (DOES NOT WORK YET)
		// includes co as well as many exceptions that must be caught in Expression.java
		proof = new Proof();
		proof.extendProof("show ((a=>q)=>((b=>q)=>((a|b)=>q)))");
		try{
			proof.extendProof("assume a=>q");
			fail("Should have caught: bad expression needs parens");
		} catch (IllegalLineException e) {}
		proof.extendProof("assume (a=>q)");
		proof.extendProof("show ((b=>q)=>((a|b)=>q))");
		try {
			proof.extendProof("assume b");
			fail("Should have caught: invalid assumption");
		} catch (IllegalLineException e) {}
		proof.extendProof("assume (b=>q) ");
		proof.extendProof("show ((a|b)=>q)");
		try{
			proof.extendProof("assume (a&b)  ");
			fail("Should have caught: illegal assumption: must be (b=>q) or ~((b=>q)=>((a|b)=>q))");
		}catch (IllegalLineException e) {}
		try{ 
			proof.extendProof("assume q");
			fail("Should have caught: illegal assumption (must be (a|b) and ~((a|b)=>q))");
		} catch (IllegalLineException e){}
		proof.extendProof("assume (a|b)");
		proof.extendProof("show q");
		try{
			proof.extendProof("assume (~q)");
			fail("Should have caught: no parens with ~");
		} catch (IllegalLineException e){}
		proof.extendProof("assume ~q");
		try {
			proof.extendProof("mt 2.2.2.1 2 a");
			fail("Should have caught: refLine 2.2.2.1 DNE");
		}catch (IllegalLineException e){}
		try {
			proof.extendProof("mt 3.2.1 2 a");
			fail("should have caught: bad inference");
		} catch (IllegalLineException e){}
		try {
			proof.extendProof("mt 3.2.2.1 2 a");
			fail("should have caught: bad inference");
		} catch (IllegalLineException e){}
		proof.extendProof("mt 3.2.2.1 2 ~a");
		proof.extendProof("mt 3.1 3.2.2.1 ~b");
		try {
			proof.extendProof("not-o-creation (~a=>(~b=>~(a|b)))");
			fail("Should have caught:theorem name misspelled");
		}catch (IllegalLineException e){}
		try{
			proof.extendProof("not-or-creation (~a=>(~b=>~(b|b)))");
			fail("Should have caught: bad theorem application: p=a, p=b, p is theorem variable");
		}catch (IllegalLineException e) {}
		proof.extendProof("not-or-creation (~a=>(~b=>~(a|b)))");
		proof.extendProof("mp 3.2.2.4 3.2.2.2 (~b=>~(a|b))");
		try{
			proof.extendProof("mq 3.2.2.3 3.2.2.5 ~(a|b) ");
			fail("Should have caught: reason mq does not exist");
		}catch (IllegalLineException e){}
		proof.extendProof("mp 3.2.2.3 3.2.2.5 ~(a|b)");
		proof.extendProof(" co 3.2.2.6 3.2.1 ((a=>q)=>((b=>q)=>((a|b)=>q)))"); //legal but useless
		proof.extendProof("co 3.2.2.6 3.2.1 q");
		try{
			proof.extendProof("ic 3.2.2.8 ((a|b)=>q)");
			fail("Should have caught: inaccessible line");
		} catch(IllegalLineException e){}
		proof.extendProof("ic 3.2.2 ((a|b)=>q) ");
		proof.extendProof("ic 3.2 ((b=>q)=>((a|b)=>q))");
		proof.extendProof("ic 3 ((a=>q)=>((b=>q)=>((a|b)=>q)))");
		assertTrue(proof.isComplete());
		
//		//proof #7 - import buildAnd (a=>(b=>(a&b)))
//		//	also import demorgan2 ((~a&~b)=>~(a|b))
		proof = new Proof();
		proof.extendProof("show ((p|q)=>(~p=>q))");
		proof.extendProof("assume (p|q)");
		proof.extendProof("show (~p=>q)");
		proof.extendProof("assume ~p");
		proof.extendProof("show q");
		proof.extendProof("assume ~q");
		proof.extendProof("buildAnd (~p=>(~q=>(~p&~q)))");
		proof.extendProof("mp 3.1 3.2.2 (~q=>(~p&~q))");
		proof.extendProof("mp 3.2.3 3.2.1 (~p&~q)");
		proof.extendProof("demorgan2 ((~p&~q)=>~(p|q))");
		proof.extendProof("mp 3.2.4 3.2.5 ~(p|q)");
		proof.extendProof("co 3.2.6 2 q");
		proof.extendProof("ic 3.2 (~p=>q)");
		proof.extendProof("ic 3 ((p|q)=>(~p=>q))");
		assertTrue(proof.isComplete());
		
		//proof #7 - import double negative
		//dn (~~a=>a)
		proof = new Proof();
		proof.extendProof("show (a=>~~a)");
		proof.extendProof("assume a");
		proof.extendProof("show ~~a");
		proof.extendProof("assume ~~~a");
		proof.extendProof("dn (~~~a=>~a)");
		proof.extendProof("mp 3.2 3.1 ~a");
		proof.extendProof("co 2 3.3 ~~a");
		proof.extendProof("ic 3 (a=>~~a)");
		assertTrue(proof.isComplete());
		
		//proof #8
		proof = new Proof();
		proof.extendProof("show (((p=>q)=>q)=>((q=>p)=>p))");
		proof.extendProof("assume ((p=>q)=>q)");
		proof.extendProof("show ((q=>p)=>p)");
		proof.extendProof("assume (q=>p)");
		proof.extendProof("show p");
		proof.extendProof("assume ~p");
		proof.extendProof("mt 3.2.1 3.1 ~q");
		proof.extendProof("mt 2 3.2.2 ~(p=>q)");
		proof.extendProof("show (p=>q)");
		proof.extendProof("assume p");
		proof.extendProof("co 3.2.4.1 3.2.1 (p=>q)");
		proof.extendProof("co 3.2.4 3.2.3 p");
		proof.extendProof("ic 3.2 ((q=>p)=>p)");
		proof.extendProof("ic 3 (((p=>q)=>q)=>((q=>p)=>p))");
		assertTrue(proof.isComplete());
		
//		//proof #9
		proof = new Proof();
		proof.extendProof("show (p=>p)");
		proof.extendProof("show (p=>p)");
		proof.extendProof("assume p");
		proof.extendProof("ic 2.1 (p=>p)");
		proof.extendProof("repeat 2 (p=>p)");
		assertTrue(proof.isComplete());
	}
	

    @Test
    public void testShow() throws Exception {
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("A"));
        Bundle bundle = new Bundle("1",tree,"2","3","4");
        Bundle bundle2 = new Bundle("1",tree,"2","3","4");

        proof.show(bundle);

        LinkedList<Bundle> resultingList = proof.getTruths();
        Bundle resultingScope = proof.getLastShow();
        assertEquals(1,resultingList.size());
        assertTrue(resultingList.contains(bundle2));
        assertEquals(resultingScope,resultingList.get(0));

    }

    @Test
    public void testAssume() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")) );
        Bundle dummyBundle = new Bundle("2",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle mainBundle = new Bundle("1",tree,"show");

        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree notTheRightTree = new BinaryTree();
        Bundle notTheRightBundle = new Bundle("1",notTheRightTree,"2");
        try {
            proof.assume(notTheRightBundle); //Throws exception
            fail("Assume should not be allowed here.");
        } catch (IllegalInferenceException i ){}

        BinaryTree notAssumeTree = new BinaryTree(new BinaryTree.TreeNode("~",new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")),null));
        Bundle assumeRightBundle1 = new Bundle("1",notAssumeTree,"2");

        proof.assume(assumeRightBundle1);
        assertEquals(assumeRightBundle1,proof.getTruths().get(proof.getTruths().size()-1));

        proof = new Proof();
        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree leftAssumeTree = new BinaryTree(new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")));
        Bundle assumeRightBundle2 = new Bundle("2",leftAssumeTree,"2");

        proof.assume(assumeRightBundle2);
        assertEquals(assumeRightBundle2,proof.getTruths().get(proof.getTruths().size()-1));


    }

    @Test
    public void testRepeat() throws IllegalLineException{

        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")) );


        Bundle dummyBundle = new Bundle("3",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle mainBundle = new Bundle("1",tree,"show");

        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree notTheRightTree = new BinaryTree();
        Bundle notTheRightBundle = new Bundle("2",notTheRightTree,"2");
//        proof.repeat(notTheRightBundle); // Should throw exception, check if you want

        BinaryTree theRightTree = new BinaryTree(new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")) );
//        theRightTree.print();
        theRightTree.getMyRoot();
        Bundle theRightBundle = new Bundle("2",theRightTree,"repeat","1");

        Bundle theWrongBundleReference = new Bundle("2",theRightTree,"repeat","2");
//        proof.repeat(theWrongBundleReference); // Throws exception for using the wrong line reference

        proof.repeat(theRightBundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertTrue(truths.get(truths.size()-1).equals(theRightBundle));
        for (int i = 0; i < truths.size()-1; i++){
            assertFalse(truths.get(i).equals(theRightBundle));
        }


    }

    @Test
    public void changeTruths() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("q"),new BinaryTree.TreeNode("q")));
        Bundle dummyBundle = new Bundle("3",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle showBundle = new Bundle("1",tree,"show");
        Bundle assumeBundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("q")),"assume");
        Bundle ICBundle = new Bundle("3",tree,"ic","2");

        proof.show(dummyBundle);
        proof.show(showBundle);
        proof.assume(assumeBundle);
        proof.changeTruths();

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals("1", truths.getLast().getLineNumber());
        assertEquals("true", truths.getLast().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());
        assertNull(truths.getLast().getRefLine1());
        assertEquals(2,truths.size());

    }


    @Test
    public void testIC() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("q"),new BinaryTree.TreeNode("q")));
        Bundle dummyBundle = new Bundle("3",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle showBundle = new Bundle("1",tree,"show");
        Bundle assumeBundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("q")),"assume");
        Bundle ICBundle = new Bundle("3",tree,"ic","2");

        proof.show(dummyBundle);
        proof.show(showBundle);
        proof.assume(assumeBundle);
        proof.IC(ICBundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals("1", truths.getLast().getLineNumber());
        assertEquals("true", truths.getLast().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());
        assertNull(truths.getLast().getRefLine1());
        assertEquals(2,truths.size());

    }

    @Test
    public void testProofTwo() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("p"),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("p"),
                                new BinaryTree.TreeNode("q")),
                        new BinaryTree.TreeNode ("q"))));

        Bundle show1Bundle = new Bundle("1",tree,"show");

        Bundle assume1Bundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("p")),"assume");
        Bundle show2Bundle = new Bundle("3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"show");
        Bundle assume2Bundle = new Bundle("3.1",new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("p"),new BinaryTree.TreeNode("q"))),"assume");
        Bundle show3Bundle = new Bundle("3.2", new BinaryTree(new BinaryTree.TreeNode("q")),"show");


        Bundle MPBundle = new Bundle("3.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","2","3.1");

        Bundle IC1Bundle = new Bundle("3.3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"ic","3.2");
        Bundle IC2Bundle = new Bundle("4", tree,"ic","3");

        proof.show(show1Bundle);
        proof.assume(assume1Bundle);
        proof.show(show2Bundle);
        proof.assume(assume2Bundle);
        proof.show(show3Bundle);
        proof.MP(MPBundle);
        proof.IC(IC1Bundle);

        proof.IC(IC2Bundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals(1,truths.size());
        assertEquals("true", truths.getFirst().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());

    }

    @Test
    public void testMP() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("p"),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("p"),
                                new BinaryTree.TreeNode("q")),
                        new BinaryTree.TreeNode ("q"))));

        Bundle dummyBundle = new Bundle("1",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle show1Bundle = new Bundle("2",tree,"show");

        Bundle assume1Bundle = new Bundle("3",new BinaryTree(new BinaryTree.TreeNode("p")),"assume");
        Bundle show2Bundle = new Bundle("4",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"show");
        Bundle assume2Bundle = new Bundle("4.1",new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("p"),new BinaryTree.TreeNode("q"))),"assume");
        Bundle show3Bundle = new Bundle("4.2", new BinaryTree(new BinaryTree.TreeNode("q")),"show");

        Bundle MPBundle = new Bundle("4.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","3","4.1");

        Bundle MPBundleWrongRefLine1 = new Bundle("4.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","3.1","4.1");
        Bundle MPBundleWrongRefLine2 = new Bundle("4.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","3","4.2");
        Bundle MPBundleWrongArgument1  = new Bundle("4.2.1",new BinaryTree(new BinaryTree.TreeNode("a")),"mp","3","4.1");
        Bundle MPBundleWrongArgument2  = new Bundle("4.2.1",new BinaryTree(new BinaryTree.TreeNode("~",
                        new BinaryTree.TreeNode("q",null,null),
                        null)),"mp","3","4.1");

        proof.show(dummyBundle);
        proof.show(show1Bundle);
        proof.assume(assume1Bundle);
        proof.show(show2Bundle);
        proof.assume(assume2Bundle);
        proof.show(show3Bundle);

        try {
            proof.MP(MPBundleWrongRefLine1);
            fail("This should capture wrong line references for MP");
        } catch (IllegalInferenceException i){}

        try {
            proof.MP(MPBundleWrongRefLine2);
            fail("This should capture wrong line references for MP");
        } catch (IllegalInferenceException i) {}

        try {
            proof.MP(MPBundleWrongArgument1);
            fail("This should capture wrong expression for MP");
        } catch (IllegalInferenceException i){
        }

        try {
            proof.MP(MPBundleWrongArgument2);
            fail("This should capture wrong expression for MP");
        } catch (IllegalInferenceException i) {}



        proof.MP(MPBundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals("1", truths.getFirst().getLineNumber());
        assertEquals("true", truths.getLast().getThrmName());
        assertTrue((new BinaryTree(new BinaryTree.TreeNode("q"))).equals(truths.getLast().getMyTree()));
        assertNull(truths.getFirst().getRefLine1());
        assertEquals(6,truths.size());

    }
    @Test
    public void testMT() throws IllegalLineException, IllegalInferenceException, Exception{
        Proof proof = new Proof();

        BinaryTree firstShowTree = new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("a",null,null),
                        new BinaryTree.TreeNode("b",null,null)),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("b",null,null),
                                new BinaryTree.TreeNode("c",null,null)),
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("a",null,null),
                                new BinaryTree.TreeNode("c",null,null)))));
        firstShowTree.print();

        Bundle firstShow = new Bundle("1",firstShowTree,"show");

        Bundle firstAssume = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("a",null,null),
                new BinaryTree.TreeNode("b",null,null))),"assume");

        Bundle secondShow = new Bundle("3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("b",null,null),
                        new BinaryTree.TreeNode("c",null,null)),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("a",null,null),
                        new BinaryTree.TreeNode("c",null,null)))),"show");
        Bundle secondAssume = new Bundle("3.1",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("b",null,null),
                new BinaryTree.TreeNode("c",null,null))),"assume");
        Bundle thirdShow = new Bundle("3.2",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("a",null,null),
                new BinaryTree.TreeNode("c",null,null))),"show");
        Bundle thirdAssume = new Bundle("3.2.1",new BinaryTree(new BinaryTree.TreeNode("a",null,null)),"assume");
        Bundle fourthShow = new Bundle("3.2.2", new BinaryTree(new BinaryTree.TreeNode("c")),"show");
        Bundle fourthAssume = new Bundle("3.2.2.1",new BinaryTree(new BinaryTree.TreeNode("~",
                new BinaryTree.TreeNode("c",null,null),null)),"assume");
        Bundle mtBundle = new Bundle("3.2.2.2",new BinaryTree(new BinaryTree.TreeNode("~",
                new BinaryTree.TreeNode("b",null,null),null)),"mt","3.2.2.1","3.1");

        Bundle mtBundleWrongLineRef1 = new Bundle("3.2.2.2",new BinaryTree(new BinaryTree.TreeNode("~",
                new BinaryTree.TreeNode("b",null,null),null)),"mt","3.2.2.3","3.1");

        Bundle mtBundleWrongLineRef2 = new Bundle("3.2.2.2",new BinaryTree(new BinaryTree.TreeNode("~",
                new BinaryTree.TreeNode("b",null,null),null)),"mt","3.2.2.1","3.2");

        proof.show(firstShow);
        proof.assume(firstAssume);
        proof.show(secondShow);
        proof.assume(secondAssume);
        proof.show(thirdShow);
        proof.assume(thirdAssume);
        proof.show(fourthShow);
        proof.show(fourthAssume);
        System.out.println(proof.getTruths().getLast());

        try {
            proof.MT(mtBundleWrongLineRef1);
            fail("This should capture wrong line references for MT");
        } catch (IllegalInferenceException i){}
        System.out.println(proof.getTruths().getLast());

        try {
            proof.MT(mtBundleWrongLineRef2);
            fail("This should capture wrong line references for MT");
        } catch (IllegalInferenceException i) {}
        System.out.println(proof.getTruths().getLast());

        int sizeBefore = proof.getTruths().size();
        proof.MT(mtBundle);
        System.out.println(proof.getTruths().getLast());
        assertEquals(sizeBefore+1,proof.getTruths().size());
        assertEquals("mt",proof.getTruths().getLast().getThrmName());
        assertEquals("3.2.2.1",proof.getLastShow().getLineNumber());
        assertEquals(proof.getTruths().get(proof.getTruths().size()-2),proof.getLastShow());
    }

    @Test
    public void testInputTheoremsChecking() {

    }
    @Test
    public void testCheckLine() {
        //Fill in after you have tested all of the other methods required for the routing that checkLine is doing.
    }

    @Test
    public void print() throws  Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("p"),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("p"),
                                new BinaryTree.TreeNode("q")),
                        new BinaryTree.TreeNode ("q"))));

        Bundle show1Bundle = new Bundle("1",tree,"show");

        Bundle assume1Bundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("p")),"assume");
        Bundle show2Bundle = new Bundle("3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"show");
        Bundle assume2Bundle = new Bundle("3.1",new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("p"),new BinaryTree.TreeNode("q"))),"assume");
        Bundle show3Bundle = new Bundle("3.2", new BinaryTree(new BinaryTree.TreeNode("q")),"show");


        Bundle MPBundle = new Bundle("3.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","2","3.1");

        Bundle IC1Bundle = new Bundle("3.3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"ic","3.2");
        Bundle IC2Bundle = new Bundle("4", tree,"ic","3");

        proof.show(show1Bundle);
        proof.assume(assume1Bundle);
        proof.show(show2Bundle);
        proof.assume(assume2Bundle);
        proof.show(show3Bundle);
        proof.MP(MPBundle);
        proof.IC(IC1Bundle);

        proof.IC(IC2Bundle);
        proof.print();

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals(1,truths.size());
        assertEquals("true", truths.getFirst().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());

    }

}
