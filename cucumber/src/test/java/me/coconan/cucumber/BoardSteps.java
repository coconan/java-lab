package me.coconan.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class BoardSteps {
    private List<List<String>> board;

    @Given("a board like this:")
    public void aBoardLikeThis(io.cucumber.datatable.DataTable dataTable) {
        board = new ArrayList<>();
        for (List<String> row : dataTable.cells()) {
            board.add(new ArrayList<>(row));
        }
    }

    @When("player x plays in row {int}, column {int}")
    public void playerXPlaysInRowColumn(Integer row, Integer col) {
        board.get(row).set(col, "x");
    }

    @Then("the board should look like this:")
    public void theBoardShouldLookLikeThis(io.cucumber.datatable.DataTable dataTable) {
        dataTable.diff(DataTable.create(board));
    }
}
