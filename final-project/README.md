Motorcycle Trip Planner
=======================

## Short description:
A JavaFX desktop app for planning motorcycle trips. Users manage riders and trips,
assign riders to trips (many-to-many), and build ordered route checkpoints for each
trip (one-to-many). All data is saved to a custom text file (trip-data.mtp) so the
app can continue between sessions.

## Main functions:
1. CRUD for Riders (name, phone, bike model, experience).
2. CRUD for Trips (title, dates, distance, description).
3. Assign / unassign riders to trips (many-to-many).
4. CRUD for route Checkpoints stored in a custom LinkedList (one-to-many with Trip).
5. Undo trip delete using a custom Stack.
6. Sort trips by distance using custom Merge Sort.
7. Search trip by title using custom Binary Search (after sorting by title).
8. Load/save all data to trip-data.mtp (custom student-designed format).

## Technical mapping to requirements:
- OOP: Person (abstract) <- Rider; Trip aggregates Checkpoints and rider IDs.
- JavaFX UI with tabs: Riders, Trips, Route Checkpoints, Sort & Search.
- File I/O: MTP1 custom format with sections [RIDERS], [TRIPS], [TRIP_RIDERS], [CHECKPOINTS].
- Custom LinkedList for checkpoints; custom Stack for undo.
- Merge Sort and Binary Search implemented by the student.
- Standard ArrayList used for riders and trips collections.

## Data file:
>  trip-data.mtp (created next to the working directory)
