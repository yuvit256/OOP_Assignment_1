package test;

import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(test.Tests.class);
    // stub method to check external dependencies compatibility
    @AfterEach
    void MemoryCheck(){
        logger.info(test.JvmUtilities::jvmInfo);
    }

    @Test
    void Test1(){ // your test
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()-> test.JvmUtilities.objectFootprint(s1)); // Shallow and deep size footprint of the object

        logger.info(()-> test.JvmUtilities.objectFootprint(s1,s2)); // Shallow and deep size footprint of the objects

        logger.info(()-> test.JvmUtilities.objectTotalSize(s1)); // computes the total (deep) size of the given object

        logger.info(() -> test.JvmUtilities.jvmInfo()); // JVM's process id, total memory allocated at the beginning of the program as well as the available number of cores
    }

    @Test
    void Test2(){
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("Yuval");
        GroupAdmin g = new GroupAdmin(usb);
        logger.info(() -> test.JvmUtilities.objectFootprint(g));
        g.append(" and");
        logger.info(() -> test.JvmUtilities.objectFootprint(g));
        g.append(" Maor");
        logger.info(() -> test.JvmUtilities.objectFootprint(g));
        g.insert(0,"Hello ");
        logger.info(() -> test.JvmUtilities.objectFootprint(g));
        g.undo();
        logger.info(() -> test.JvmUtilities.objectFootprint(g));
    }

    @Test
    public void Test3() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("Maor");
        GroupAdmin g =new GroupAdmin(usb);
        logger.info(() -> test.JvmUtilities.objectTotalSize(g));
        g.append(" is Great");
        logger.info(() -> test.JvmUtilities.objectTotalSize(g));
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        g.register(m1);
        g.register(m2);
        g.register(m3);
        g.append("!!!");
        //Checking if they have the same size
        logger.info(() -> test.JvmUtilities.objectTotalSize(m1));
        logger.info(() -> test.JvmUtilities.objectTotalSize(m2));
        assertEquals(test.JvmUtilities.objectTotalSize(m1), test.JvmUtilities.objectTotalSize(m2));
        g.undo();
        assertEquals("Maor is Great", m1.toString());
        g.unregister(m3);
        g.delete(0, 8);
        assertEquals("Great", m1.toString());
        assertEquals(m2.toString(), m2.toString());
        g.insert(0, "Yuval is ");
        assertEquals("Yuval is Great", m1.toString());
        logger.info(() -> test.JvmUtilities.objectTotalSize(g)); // Size before unregister
        g.unregister(m2);
        g.unregister(m1);
        logger.info(() -> test.JvmUtilities.objectTotalSize(g)); // Size  after unregister
        // the diffrence is 528 - 496 = 32
    }

}
