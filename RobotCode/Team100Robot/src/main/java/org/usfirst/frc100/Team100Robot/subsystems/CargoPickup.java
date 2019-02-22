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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc100.Team100Robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * 
 */
public class CargoPickup extends Subsystem {

    //public WPI_TalonSRX cargoTilt;
    public VictorSP cargoRoller1;
    public VictorSP cargoRoller2;
   // public Solenoid cargoIntakeArmPivot;
    //public Solenoid cargoIntakeArmPivot2;
    public DoubleSolenoid cargoIntakePivotDoubleSolenoid;
    public CargoPickup() {
   
        cargoRoller1 = new VictorSP(Constants.CARGO_PICKUP_ROLLER1_PWM);
        addChild("CargoRoller1", cargoRoller1);
        cargoRoller1.setInverted(false);
        
        cargoRoller2 = new VictorSP(Constants.CARGO_PICKUP_ROLLER2_PWM);
        addChild("CargoRoller2", cargoRoller2);
        cargoRoller2.setInverted(false);
        
        cargoIntakePivotDoubleSolenoid = new DoubleSolenoid(Constants.CARGO_GROUND_PICKUP_PCMID, Constants.CARGO_GROUND_PICKUP2_PCMID);

        
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new MySpecialCommand());
        // setDefaultCommand(new CargoManipulator());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("6 PO",cargoRoller1.get());
        
        SmartDashboard.putData("CargoIntakeArmPivot",cargoIntakePivotDoubleSolenoid);
    }

    public void setOutput(double output){
        System.out.println("SETTING TO "+output);
        cargoRoller1.set(output);
        cargoRoller2.set(output);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

