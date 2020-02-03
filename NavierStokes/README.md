# Navier Stokes

This project represents a Navier Stokes simulator.

## Motivation/BackStory

I developed this project during the 1st year of my bachelor's degree (circa 2015). As I got into programming due to game development, I wanted to improve my knowledge in graphical computation. I first started with a simple marching square algorithm and then, I had stumbled across the Navier Stokes equations, which basically describe how fluids behave. I had also found a paper, from the developers of Maya, explaning how to implement this in C++.

As such, with some guidance of a professor from ISEP (João Paulo Pereira), I developed the simulator in Java.


## Comments/Extra Notes

The simulator works as intended. Despite this, there are some slider configurations that may "break" the simulation. For example, if the viscosity is too high and the user has inserted many particles, it will be hard to reset the environment (as the entire "space" will have too much of the fluid).

The project contains instructions on how to use the simulator. The code also supports changing colors, even though no UI elements are present to do so.

## Expected Changes

This project has no expected changes, and is considered "completed". The last time it was touched was around 2018-2019, just to move it to another hard drive (and to refine slider values).