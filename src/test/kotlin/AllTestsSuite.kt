import header.HeaderBuilderTest
import header.HeaderReaderTest
import header.HttpHeaderTest
import method.MethodValidatorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import request.RequestReaderTest
import request.RequestTest
import response.BadRequestResponseTest
import response.BinaryResponseTest
import response.JsonResponseTest
import response.NotImplementedResponseTest

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
        BinaryResponseTest::class,
        JsonResponseTest::class,
        BadRequestResponseTest::class,
        NotImplementedResponseTest::class
)

class JunitTestSuite {
}
