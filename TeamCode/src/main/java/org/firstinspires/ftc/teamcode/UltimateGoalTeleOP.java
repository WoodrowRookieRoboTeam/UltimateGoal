package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

@TeleOp(name="Ultimate Goal TeleOP", group="Linear Opmode")
public class UltimateGoalTeleOP extends OpMode {

    public final static int MAX_ENCODER = 3700;

    private DcMotor frontRightMotor = null;
    private DcMotor frontLeftMotor = null;
    private DcMotor backRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor shooterMotor;
    private double speed = 1;



    public void init() {
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");


        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);





    }

    private void resetDriveEncoders(){
        //Stop the motors and reset the encoders to zero
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Make sure we re-enable the use of encoders
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    private int[] getMotorEncoders(){
        int[] result = new int[4];

        result[0] = frontLeftMotor.getCurrentPosition();
        result[1] = frontRightMotor.getCurrentPosition();
        result[2] = backLeftMotor.getCurrentPosition();
        result[3] = backRightMotor.getCurrentPosition();

        return result;
    }

    /**
     * Initialize the gyro
     */

    public void loop() {

        TeleOP1();
    }


    private void TeleOP1(){



        //strafe and drive with left stick
        double verticalAxis = -gamepad1.left_stick_y;
        double horizontalAxis = -gamepad1.left_stick_x;
        double rotation = -gamepad1.right_stick_x*.75;

        frontLeftMotor.setPower(((verticalAxis - horizontalAxis)* speed - rotation)*speed);
        frontRightMotor.setPower(((verticalAxis + horizontalAxis)* speed + rotation)*speed);
        backLeftMotor.setPower(((verticalAxis + horizontalAxis)* speed - rotation)*speed);
        backRightMotor.setPower(((verticalAxis - horizontalAxis)* speed + rotation)*speed);


        if(gamepad1.y){
            shooterMotor.setPower(1);
        }else{
            shooterMotor.setPower(0);
        }if(gamepad1.a){
            shooterMotor.setPower(-1);
        }else{
            shooterMotor.setPower(0);
        }



        if(gamepad1.left_stick_button){
            speed = .65;
        }
        if(gamepad1.right_stick_button){
            speed = 1;
        }







    }
}
