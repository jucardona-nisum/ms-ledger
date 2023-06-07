package com.nisum.hack.ms.ledger.steps;

import com.nisum.hack.ms.ledger.base.AcceptanceBaseStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SumValuesOperationStep extends AcceptanceBaseStep {

    private ResponseEntity<Integer> result;

    @When("^Sending the parameters: (\\d+) and (\\d+)$")
    public void sending_the_parameters(int arg1, int arg2) throws URISyntaxException {
        List<Integer> values = new ArrayList<>();
        values.add(arg1);
        values.add(arg2);
        result = call(values, HttpMethod.POST, Integer.class);
    }

    @Then("^The response is (\\d+)$")
    public void the_response_is(int arg1) {
        if (result != null) {
            int body = result.getBody();
            Assert.assertEquals(body, arg1);
        } else {
            Assert.fail();
        }
    }
}
