# riemann-harness


A harness for building and testing riemann configs. At the moment this setup
is a bit Mac-specific, sorry Linux-users. You'll know best what needs to be
changed to get it to work for you.

## Running

The command to run riemann, riemann-dash, and the riemann-harness together is:

```bash
make run
```

To nREPL in to the running harness, and start sending test events, run:

```bash
$ make repl
; lein repl :connect $(docker-machine ip default):4001
; Connecting to nREPL at 192.168.xx.xxx:4001
; REPL-y 0.3.7, nREPL 0.2.10
; Clojure 1.7.0
; OpenJDK 64-Bit Server VM 1.8.0_66-internal-b01
;     Docs: (doc function-name-here)
;           (find-doc "part-of-name-here")
;   Source: (source function-name-here)
;  Javadoc: (javadoc java-object-or-class-here)
;     Exit: Control+D or (exit) or (quit)
;  Results: Stored in vars *1, *2, *3, an exception in *e
;
riemann-harness.core=> (user/go)
```

To open riemann-dash in your browser and see the events coming in, run:

```bash
make dash
```

To reload changes to `riemann.config`,

```bash
make reload
```

## Event Generators

Test events are generated by a vector of event generators. An event generator
is a 0-arity function that returns a two element `[event, interval]` vector.
The value of `event` should be the event to be sent to riemann now, and `interval`
should be the time, in milliseconds, until the next event should be sent.

Three examples of generators are shown in the `riemann-harness.generators` namespace.
The first simply returns an identical event and interval every time.
The second returns events and intervals with randomized values in particular ranges.
The third reads a history of events from a file, and sends events identical apart
from their times - these are calculated from the current time to be sent at the
same intervals as the historical events.
