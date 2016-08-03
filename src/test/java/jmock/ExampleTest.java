package jmock;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by besil on 09/06/2016.
 */
public class ExampleTest extends EasyMockSupport {

    @TestSubject
    private final ClassUnderTest classUnderTest = new ClassUnderTest(); // 2
    @Rule
    public EasyMockRule rule = new EasyMockRule(this);
    @Mock
    private Collaborator collaborator; // 1

    @Test
    public void addDocument() {
        collaborator.documentAdded("New Document"); // 3
        replayAll(); // 4
        classUnderTest.addDocument("New Document", "content".getBytes()); // 5
        verifyAll(); // 6
    }
}