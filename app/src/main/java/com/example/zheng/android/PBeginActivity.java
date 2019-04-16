package com.example.zheng.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PBeginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbegin);
        State initial = pp.getInitialState();
        tv =  findViewById(R.id.puzzleNow);
        tv.setText(initial.toString());
        tv.getAutoSizeTextType();
        message = findViewById(R.id.messageClick);
        puzzState = (PuzzleState)pp.getCurrentState();
        init();
        setDimensions();

        this.aStarSolver = new AStarSolver(pp);
        solver = this.aStarSolver;
        this.statistics = new Statistics();
        moveCount = 0;
        s = findViewById(R.id.sol);
        sb = findViewById(R.id.solvebutton);
    }

    private void init() {
        myGridView = findViewById(R.id.grid);
        myGridView.setNumColumns(COLUMNS);
        tileList = new String[DIMENSIONS];
        for (int i =0; i<DIMENSIONS; i++){
            tileList[i] = String.valueOf(i);
        }
    }

    private void setDimensions() {
        ViewTreeObserver vto = myGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                myGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = myGridView.getMeasuredWidth();
                int displayHeight = myGridView.getMeasuredHeight();

                int statusBarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusBarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());
            }
        });
    }

    /**
     * Solve Button
     * @param view
     */
    public void solve(View view){
        solver.solve();
        (this.solution = this.solver.getSolution()).next();
        tv.setText((this.pp.getFinalState()==null) ? "Not applicable" :
                this.pp.getFinalState().toString());
        tv.getAutoSizeTextType();
        s.setText(solver.getStatistics().toString());
        sb.setEnabled(false);

    }

    /**
     * Next buttion
     * @param view
     */
    public void next(View view){
        if (this.solution.hasNext()) {
            final Vertex v = this.solution.next();
            final State s = (State)v.getData();
            this.update(s);

            if (this.isProblemSolved()) {
                this.currentMessage = "You solved the problem";
            }

            tv.setText(this.pp.getCurrentState().toString());
            message.setText(this.currentMessage);
            tv.getAutoSizeTextType();
            message.getAutoSizeTextType();
        }
    }
    /**
     * Resets the problem to its initial state.
     * Also resets the move count and problem solved status.
     */
    public void reset(View view) {
        moveCount = 0;
        State reset = pp.getInitialState();
        update(reset);
        this.currentMessage = "";
        tv.setText(this.pp.getCurrentState().toString());
        tv.getAutoSizeTextType();
        setProblemSolved(false);
        sb.setEnabled(true);
        s.setText("");
    }
    /**
     * Updates the current state of the problem to the given state.
     * Also increments the move count and checks the problem solved status.
     * @param s
     */
    private void update(State s){
        moveCount++;
        pp.setCurrentState(s);
        if(pp.success()){
            setProblemSolved(true);
        }
    }

    private void setProblemSolved(boolean b) {
        if(b ==true){
            this.problemSolved = true;
        }
        else{
            this.problemSolved = false;
        }
    }
    public boolean isProblemSolved() {
        return problemSolved;
    }

    public boolean isMoveLegal() {
        return moveLegal;
    }

    /**
     * Tries a move on the current state of the problem, updating the
     * current state if required.
     * @param move the move to try, as a string
     */
    public void tryMove(String move) {
        moveLegal = true;
        State next = pm.doMove(move, pp.getCurrentState());
        if (next != null) {
            update(next);
        }
        else {
            moveLegal = false;
        }
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }
    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.space_s);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.number_one);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.number_two);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.number_three);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(R.drawable.number_4);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(R.drawable.number_5);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(R.drawable.number_6);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(R.drawable.number_7);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(R.drawable.number_8);

            buttons.add(button);
        }

        myGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }
    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (isSolved()) Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }
    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }

    public void try1(View v){
        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 1.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 1.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }
    public void try2(View v){

        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 2.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 2.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }
    public void try3(View v){
        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 3.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 3.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }

    public void try4(View v){
        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 4.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 4.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }

    public void try5(View v){
        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 5.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 5.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }

    public void try6(View v){
        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 6.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 6.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }

    public void try7(View v){
        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 7.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 7.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }

    public void try8(View v){
        int row = puzzState.getLocation(1).getRow();
        int col = puzzState.getLocation(1).getColumn();
        int r2 = puzzState.getLocation(2).getRow();
        int c2 = puzzState.getLocation(2).getColumn();
        int r3 = puzzState.getLocation(3).getRow();
        int c3 = puzzState.getLocation(3).getColumn();
        int r4 = puzzState.getLocation(4).getRow();
        int c4 = puzzState.getLocation(4).getColumn();
        int r5 = puzzState.getLocation(5).getRow();
        int c5 = puzzState.getLocation(5).getColumn();
        int r6 = puzzState.getLocation(6).getRow();
        int c6 = puzzState.getLocation(6).getColumn();
        int r7 = puzzState.getLocation(7).getRow();
        int c7 = puzzState.getLocation(7).getColumn();
        int r8 = puzzState.getLocation(8).getRow();
        int c8 = puzzState.getLocation(8).getColumn();
        int rowEmpty = puzzState.getLocation(0).getRow();
        int colEmpty = puzzState.getLocation(0).getColumn();
        if(pm.doMove("SLIDE TILE 8.", pp.getCurrentState() ) == null){
            tv.setText("Invalid Move");
        }
        else{
            pp.setCurrentState(pm.doMove("SLIDE TILE 8.",pp.getCurrentState()));
            ps = (PuzzleState)pp.getCurrentState();
        }
        if(pp.success()){
            tv.setText("Congrats! You solved the problem!");
        }
    }
    private TextView tv;
    private TextView message;
    private boolean problemSolved;
    private int moveCount;
    private boolean moveLegal;

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;
    private static String[] tileList;
    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    private Statistics statistics;
    private PuzzleProblem pp = new PuzzleProblem();
    private PuzzleMover pm = new PuzzleMover();
    private PuzzleState puzzState;
    private State ps = (PuzzleState) pp.getCurrentState();

    private List<String> moves = pm.getMoveNames();
    private Solver solver;
    private Solver aStarSolver;
    private String currentMessage;
    private Solution solution;
    private static GestureDetectGridView myGridView;
    private TextView s;
    private Button sb;

}
