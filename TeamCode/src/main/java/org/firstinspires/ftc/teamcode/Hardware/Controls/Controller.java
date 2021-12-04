package org.firstinspires.ftc.teamcode.Hardware.Controls;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.opencv.core.Point;

public class
Controller {

    private final Gamepad gamepad;

    public Controller(Gamepad gamepad) {
        this.gamepad = gamepad;
        cross = new Button();
        circle = new Button();
        triangle = new Button();
        square = new Button();
        up = new Button();
        down = new Button();
        left = new Button();
        right = new Button();
        RB = new Button();
        LB = new Button();
        RS = new Button();
        LS = new Button();
        share = new Button();
        touchpad = new Button();
        RTrigger = new Button();
        LTrigger = new Button();


    }


    public Button cross,circle,triangle,square,up,down,left,right,RB,LB,RS,LS,share,touchpad,RTrigger,LTrigger;



    public static class Button{

        private boolean IsPressed = false;
        private boolean LastPressed = false;
        private boolean Toggle = false;
        private boolean IsTapped = false;
        private boolean released = false;
        float value = 0;

        public void Update(boolean _button){
            IsPressed = _button;

            if(IsPressed && !LastPressed){
                IsTapped = true;
            }else{
                IsTapped = false;
            }

            if (IsTapped){
                Toggle = !Toggle;
            }
            LastPressed = IsPressed;

        }

        public void Update(float inputFloat){
            value = inputFloat;
            Update(inputFloat >= .7);
        }


        public float getValue() {
            return value;
        }

        public boolean press(){ return IsPressed; }

        public boolean toggle(){ return Toggle; }

        public boolean tap(){ return   IsTapped; }





    }
    public void controllerUpdate(){
        cross.Update(gamepad.cross);
        circle.Update(gamepad.circle);
        triangle.Update(gamepad.triangle);
        square.Update(gamepad.square);
        up.Update(gamepad.dpad_up);
        down.Update(gamepad.dpad_down);
        left.Update(gamepad.dpad_left);
        right.Update(gamepad.dpad_right);
        RB.Update(gamepad.right_bumper);
        LB.Update(gamepad.left_bumper);
        RS.Update(gamepad.right_stick_button);
        LS.Update(gamepad.left_stick_button);
        share.Update(gamepad.share);
        touchpad.Update(gamepad.touchpad);
        RTrigger.Update(gamepad.right_trigger);
        LTrigger.Update(gamepad.left_trigger);


    }

    public Point leftStick(){
        return new Point(gamepad.left_stick_x,gamepad.left_stick_y);
    }
    public Point rightStick(){
        return new Point(-gamepad.right_stick_x,-gamepad.right_stick_y);
    }

}


