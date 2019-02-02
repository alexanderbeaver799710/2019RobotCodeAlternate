// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc100.Team100Robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc100.Team100Robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class HatchFloorPickup extends Subsystem {

    private WPI_TalonSRX tilt;
    private VictorSP roller;
    private Solenoid hatchFloorPickup;

    public HatchFloorPickup() {
        tilt = new WPI_TalonSRX(Constants.HATCH_PICKUP_TILT_CANID);
        
        roller = new VictorSP(Constants.HATCH_PICKUP_ROLLER_PWM);
        addChild("Roller", roller);
        roller.setInverted(false);
        
        hatchFloorPickup = new Solenoid(Constants.PCM_CANID, Constants.HATCH_PICKUP_PCMID);
        addChild("HatchFloorPickup",hatchFloorPickup);
    }
 
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

