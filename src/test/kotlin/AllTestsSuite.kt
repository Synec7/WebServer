
import header.HeaderBuilderTest
import header.HeaderReaderTest
import header.HttpHeaderTest
import method.MethodValidatorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import request.RequestReaderTest
import request.RequestTest
import response.*

/**
 * Created by Vincente on 4/15/17.
 */

@RunWith(Suite::class)

@Suite.SuiteClasses(
        HeaderBuilderTest::class,
        HeaderReaderTest::class,
        HttpHeaderTest::class,
        MethodValidatorTest::class,
        RequestHandlerTest::class,
        RequestReaderTest::class,
        RequestTest::class,
        BadRequestResponseTest::class,
        BinaryResponseTest::class,
        JsonResponseTest::class,
        NotImplementedResponseTest::class,
        ResponseHandlerTest::class
)

class JunitTestSuite
