# NimohSurvival Plugin Documentation

## API Components

### Command System
- BaseCommand: Abstract foundation for all commands with permission handling and player validation
- BaseParticleCommand: Specialized command base for particle effects with suggestion system

### GUI System
- Border: Utility class for creating standardized GUI borders using glass panes
- Heads: Custom player head creation system with texture URL support

### Particle System
- ParticleShape: Enum defining available particle effect shapes (CIRCLE, SPHERE, SQUARE, etc.)
- ParticleShapes: Complex particle effect generator with multiple shape patterns

## Commands

### Particle Commands
- FullParticleCommand: Creates complete particle shape effects
- ParticleCommand: Basic particle effect generation
- PulsingParticleCommand: Creates pulsing particle animations

### Teleport Commands
- ArenaCommand: Teleports players to arena
- HomeCommand: Opens home management GUI
- LobbyCommand: Server transfer command using Velocity
- MarketCommand: Teleports to market area
- SpawnCommand: Teleports to spawn location
- TeamWarpsCommand: Team-specific warp management

## GUI Components
- HomeGui: Home management interface (36 slots)
- TeamGui: Team management interface (9 slots)
- TeamWarpsGui: Team warp management (9 slots)
- WarpGui: General warp menu (18 slots)

## Listeners
- TeamGuiChatListener: Processes chat input for team GUI interactions
- TeamGuiListener: Handles team GUI click events

## Core Plugin
- Bootstrap: Plugin initialization and command registration
- Survival: Main plugin class managing lifecycle and core systems

## Features
- Integrated particle system with multiple effect types
- GUI-based navigation system
- Team management system
- Multi-server support via Velocity
- Custom inventory interfaces
- Permission-based command system
