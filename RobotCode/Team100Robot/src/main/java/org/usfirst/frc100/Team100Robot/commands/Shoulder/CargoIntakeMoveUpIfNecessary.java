/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Shoulder;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CargoIntakeMoveUpIfNecessary extends Command {
  boolean done = false;
  public CargoIntakeMoveUpIfNecessary() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.global);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.elevator.setpointLevel == 4){
      done = false;
      SmartDashboard.putBoolean("CIMUIN DONE",false);

      new ShoulderHoming().start();
    }else{
      done = true;
      System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ NOT NECESSARY TO PIVOT");
    }

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("CIMUIN Execute");
    if(Math.abs(Robot.carriageShoulder.currentSetpoint-Robot.carriageShoulder.carriageShoulderMotor.getSelectedSensorPosition())<Constants.SHOULDER_BUFFER){
      done = true;
      SmartDashboard.putBoolean("CIMUIN DONE",true);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
