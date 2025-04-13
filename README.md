<div style="align-items: center; text-align: center">
    <div style="max-height: 200px;">
    <img src="https://i.imgur.com/eDcB5uc.png" alt="Infrastructure Toolkit Header Image"/>
    </div>
    <em>Bundle's Messaging & Infrastructure Developer Tools.</em>
</div>

## Modules

- `MessageBus` is a system designed to make messaging platform-agnostic.
  - `messagebus-api` is the Java API for projects using MessageBus. 
  - `messagebus-common` is used internally for providers, and plugins that handle messages.
  - `messagebus-paper` is the Paper implementation for MessageBus
  - `messagebus-velocity` is the Velocity implementation for MessageBus
- `Orchestrator` is the API wrapper for our actual infrastructure to be used to spin game servers up & down with ease.
  - `orchestrator-api` is an API module for interacting with our container orchestration.
  - `orchestrator-common` is a module to be used with different platforms, e.g. Velocity, Minestom, Paper.
  - `orchestrator-velocity` makes it easy to spin up & down game servers when using Velocity. 

## What is the Bundle Developer Toolkit?

The Bundle Developer Toolkit was created to ensure quality regardless of overall experience. These Developer Tools are used at Bundle Group to make sure our creations are crafted elegantly and have good code quality. 

Bundle's Developer Toolkit is developed under a GNU Affero General Public License (version 3), which means that any modifications or derivative works of the licensed software is also required to be released under the same license.

---

(c) 2025 Bundle Group Ltd. All Rights Reserved