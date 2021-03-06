/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Procedures.ElevatorTravel;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorCargoLevel1;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorCargoLevel2;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorUpdateDesiredEndpoint.ElevatorUpdateDesiredSetpointLevel3;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmConditionalDown;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmDown;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmUp;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.CargoIntakeMoveUpIfNecessary;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHoming;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderLevel;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderLevel2Cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevatorGoToLevel2CargoProcedure extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorGoToLevel2CargoProcedure() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    
    /*requires(Robot.elevator);
    requires(Robot.cargoPickup);
    requires(Robot.carriageShoulder);*/
    addSequential(new CargoIntakeMoveUpIfNecessary());
    addSequential(new ElevatorUpdateDesiredSetpointLevel3()); //test/
    addSequential(new IntakeArmConditionalDown());
    //addSequential(new IntakeArmDown());
    addSequential(new ShoulderHoming());
    addSequential(new ElevatorCargoLevel2());
    addSequential(new ShoulderLevel());

    addSequential(new IntakeArmUp());
  }
}
