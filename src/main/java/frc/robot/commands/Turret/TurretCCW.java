// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;
import frc.robot.subsystems.Turret;
import frc.robot.Constants.TurretConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretCCW extends CommandBase {
  private Turret turret;
  private Joystick controller;
  private double speed;
  /** Creates a new TurretClockwise. */
  public TurretCCW(Turret turret, Joystick controller) {
    this.turret = turret;
    this.controller = controller;
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turret.setTurretSpeed(-TurretConstants.TURRET_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.turretStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if ((speed > 0.008) && turret.isTCWLimit()) {
      // if mast is going up and top limit is triggered
      // the 0.008 is because when the axis is at rest, it reads 0.0078125 so doing speed > 0.008 acts as a deadzone
      return true;
    } else if ((speed < 0) && turret.isTCCWLimit()) {
      turret.resetTurretEncoder();
      return true;
    } else {
      return false;
    }
  }
}