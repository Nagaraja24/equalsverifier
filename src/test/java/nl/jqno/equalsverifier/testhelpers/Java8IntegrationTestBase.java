package nl.jqno.equalsverifier.testhelpers;

import nl.jqno.equalsverifier.internal.reflection.ConditionalInstantiator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class Java8IntegrationTestBase extends IntegrationTestBase {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private ConditionalCompiler compiler;

    @Before
    public void setUp() throws IOException {
        File tempFileLocation = tempFolder.newFolder();
        compiler = new ConditionalCompiler(tempFileLocation);
    }

    @After
    public void tearDown() {
        compiler.close();
    }

    public Class<?> compile(String className, String code) {
        return compiler.compile(className, code);
    }

    public boolean isJava8Available() {
        return isTypeAvailable("java.util.Optional");
    }

    private boolean isTypeAvailable(String fullyQualifiedTypeName) {
        return new ConditionalInstantiator(fullyQualifiedTypeName).resolve() != null;
    }
}
