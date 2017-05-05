
import header.HeaderReaderTest
import header.HeadersTest
import header.HttpHeaderTest
import method.MethodValidatorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import request.HttpRequestReaderTest
import request.HttpRequestTest
import response.*

/**
 * Created by Vincente on 4/15/17.
 */

@RunWith(Suite::class)

@Suite.SuiteClasses(
        HeadersTest::class,
        HeaderReaderTest::class,
        HttpHeaderTest::class,
        MethodValidatorTest::class,
//        HttpRequestHandlerTest::class,
        HttpRequestReaderTest::class,
        HttpRequestTest::class,
        BadRequestHttpResponseTest::class,
        BinaryHttpResponseTest::class,
        JsonHttpResponseTest::class,
        NotImplementedHttpResponseTest::class,
        WebHttpResponseHandlerTest::class
)

class JunitTestSuite
