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
    public void a_board_like_this(io.cucumber.datatable.DataTable dataTable) {
        board = new ArrayList<>();
        for (List<String> row : dataTable.cells()) {
            board.add(new ArrayList<>(row));
        }
    }

    @When("player x plays in row {int}, column {int}")
    public void player_x_plays_in_row_column(Integer row, Integer col) {
        board.get(row).set(col, "x");
    }

    @Then("the board should look like this:")
    public void the_board_should_look_like_this(io.cucumber.datatable.DataTable dataTable) {
        dataTable.diff(DataTable.create(board));
    }
}
