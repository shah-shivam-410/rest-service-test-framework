package listeneres;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import reporting.ExtentLogger;

public class RestAssuredFilterImpl implements Filter {
	
	public static ExtentLogger logger = new ExtentLogger();

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		logger.info("\n-------------REQUEST-------------\n");
		logger.info(requestSpec.getURI());
		logger.infoBlock(requestSpec.getBody());
		
		Response response = ctx.next(requestSpec, responseSpec);

		logger.info("\n-------------RESPONSE-------------\n");
		logger.info(response.statusLine());
		logger.infoBlock(response.body().asPrettyString());
		
		return response;
	}

}
