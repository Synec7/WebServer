
import app.http.HeaderReaderTest
import app.http.HeadersTest
import app.http.HttpHeaderTest
import app.http.request.HttpRequestReaderTest
import app.http.request.HttpRequestTest
import app.http.request.MethodValidatorTest
import app.http.response.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

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
