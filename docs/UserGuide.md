---
layout: page
title: User Guide
---
# Wanderlust v1.2 User Guide

## Introduction

WanderLust helps tech-savvy travellers to plan their trips in a structured and efficient manner by providing them with a holistic travel planner.
It is optimized for CLI users so that destinations and details can be added faster by typing in commands.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
* [Introduction](#introduction)
* [Setting Up \[Coming soon\]](#setting-up-coming-soon)
* [Command Summary](#command-summary)
* [Tags](#tags)
    * [Activity Tag](#activity-tag)
    * [Accommodation Tag](#accommodation-tag)
    * [Person Tag](#person-tag)
    * [Travel Plan Tag](#travel-plan-tag)
    * [Wishlist Tag](#wishlist-tag)
* [Features](#features)
    * [Goto](#goto)
        1. [Goto a Travel Plan (G)](#1-goto-a-travel-plan-g)
        2. [Goto Wishlist (G)](#2-goto-wishlist-g)
    * [Add](#add)
        1. [Adding a Travel Plan (G)](#1-adding-a-travel-plan-g)
        2. [Adding an Activity (L)](#2-adding-an-activity-l)
        3. [Adding an Accommodation (L)](#3-adding-an-accommodation-l)
        4. [Adding a Person (L)](#4-adding-a-person-l)
    * [Delete](#delete)
        1. [Deleting a Travel Plan (G)](#1-deleting-a-travel-plan-g)
        2. [Deleting an Activity (L)](#2-deleting-an-activity-l)
        3. [Deleting an Accommodation (L)](#3-deleting-an-accommodation-l)
        4. [Deleting a Person (L)](#4-deleting-a-person-l)
    * [Edit](#edit)
        1. [Editing a Travel Plan (G)](#1-editing-a-travel-plan-g)
        2. [Editing an Activity (L)](#2-editing-an-activity-l)
        3. [Editing an Accommodation (L)](#3-editing-an-accommodation-l)
        4. [Editing a Person (L)](#4-editing-a-person-l)
* [FAQ \[Coming soon\]](#faq-coming-soon)

--------------------------------------------------------------------------------------------------------------------

## Setting up [Coming soon]

--------------------------------------------------------------------------------------------------------------------
## Command Summary

There are a total of 5 general commands.
The table briefly describes the commands and its usage. Full details will be given in the next section.

Command | Parameters | Description
------------ | ------------- | -------------
`add -OBJECT` | `OBJECT` activity/ accommodation/ person/ travelplan | Creates the given object type
`delete -OBJECT` | `OBJECT` activity/ accommodation/ person/ travelplan | Deletes the given object type
`edit -OBJECT` | `OBJECT` activity/ accommodation/ person/ travelplan | Edits the details of the given object type
`goto PLAN_NAME` | `PLAN_NAME` nameOfTravelPlan/ wishlist | Navigate to the specific travel plan/ wishlist

--------------------------------------------------------------------------------------------------------------------
## Tags

The table below shows the type of tag each different object can be assigned to.
Do note that tags are compulsory when creating the object.

#### Activity Tag
Name of Tag | Description
------------ | -------------
`n/NAME` | Name of the activity.
`l/LOCATION` | Location/ Address of the activity.
`i/LEVEL_OF_IMPORTANCE` | The priority assigned to the activity.
`c/cost` | Cost of the activity, if any.
`d/DATE_AND_TIME` | Date and Time intended to do the activity. Format of date is in DD-MM-YYYY and format of time is HHMM (24h clock).


#### Accommodation Tag
Name of Tag | Description
------------ | -------------
`n/NAME` | Name of the accommodation.
`l/LOCATION` | Location/ Address of the accommodation.
`c/cost` | Cost of the accommodation, if any.
`sd/START_DATE` | Start date of accommodation in the format of DD-MM-YYYY
`ed/END_DATE` | End date of accommodation in the format of DD-MM-YYYY

#### Friend Tag
Name of Tag | Description
------------ | -------------
`n/NAME` | Name of the friend.
`m/MOBILE_NUMBER` | Mobile number of the person cell mobile
`p/PASSPORT_NUMBER` | Passport number of the person passport

#### Travel Plan Tag
Name of Tag | Description
------------ | -------------
`n/NAME` | Name of the travel plan.
`sd/START_DATE` | Start date of travel in the format of DD-MM-YYYY
`ed/END_DATE` | End date of travel in the format of DD-MM-YYYY

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -activity n/NAME`, `NAME` is a parameter which can be used as `add -activity n/Cultural Visit`.

</div>

## Goto

The goto command is used to navigate to various directories within the travel planner. The GUI display depends on the
state of the current directory, which is changed using the goto command. On start, the default directory of the program
is `wishlist`.<br/>
<br/>

>Directories in Wanderlust include:
>1. `wishlist`
>2. `travelplan index`<br/>

<br/>
This allows users to easily add, delete, view and edit information within the wishlist or a specific travel plan without
having to type their names in each command. This also means that **some commands can only be used locally within a
certain directory, and responses to these commands will depend on the current directory.** Commands that can be used
globally and locally are tagged (G) and (L) respectively in the **features section** of this user guide. <br/>
<br/>

### 1. Goto a Travel Plan (G)
Navigates the UI to a specific travel plan.

Format: `goto -travelplan index`

Example: `goto -travelplan 2`

### 2. Goto Wishlist (G)
Navigates the UI to the wishlist.

Format: `goto -wishlist`

Example: `goto -wishlist`

## Add

### 1. Adding a Travel Plan (G)
Creates a new travel plan and adds it to Wanderlust’s travel planner.
Start and end date can be optional, but they must exist as a pair. Format of date is in DD-MM-YYYY.

Format: `add -travelplan n/NAME sd/START_DATE ed/END_DATE`

Example: `add -travelplan n/France sd/15-09-2020 ed/30-09-2020`


### 2. Adding an Activity (L)
Creates a new activity and adds it to the travel plan/wishlist in the current directory. Date and time can be optional, but they must exist as a pair.
Format of date is in DD-MM-YYYY and format of time is HHMM (24h clock).

Format: `add -activity n/NAME i/LEVEL_OF_IMPORTANCE l/LOCATION c/COST d/YYYY-MM-DD HH:mm`

Example: `add -activity n/Universal Studios Singapore i/5 l/Sentosa c/88 d/16-09-2020 10:10`


### 3. Adding an Accommodation (L)
Creates a new accommodation that contains information about the place of stay and adds it to the travel plan in the current directory.
This command can only be used within a travel plan. Use goto NAME_OF_TRAVEL_PLAN before adding accommodations.

Format: `add -accommodation n/NAME l/LOCATION c/COST sd/YYYY-MM-DD ed/YYYY-MM-DD`

Example: `add -accommodation n/St Regis Hotel l/Orchard Road c/250 sd/2020-10-11 ed/2020-10-15`

### 4. Adding a Friend (L)
Creates a person object that contains basic information about the user and
other travellers and adds it to the travel plan in the current directory.

Format: `add -friend n/NAME m/MOBILE_NUMBER p/PASSPORT_NUMBER`

Example: `add -friend n/John m/81234567 p/E1234567H`

## Delete

### 1. Deleting a Travel Plan (G)

Deletes a travel plan at a given index from the travel planner.

Format: `delete -travelplan INDEX`

Example: `delete -travelplan 3`

### 2. Deleting an Activity (L)

Deletes an activity at a given index from the travel plan/wishlist in the current directory.

Format: `delete -activity INDEX`

Example: `delete -activity 3`

### 3. Deleting an Accommodation (L)

Deletes an accommodation at a given index from the travel plan in the current directory.
This command can only be used within a travel plan. Use `goto NAME_OF_TRAVEL_PLAN` before deleting accommodations.

Format: `delete -accommodation INDEX`

Example: `delete -accommodation 2`

### 4. Deleting a Friend (L)

Deletes a friend from at a given index from the travel plan in the current directory.
This command can only be used within a travel plan. Use `goto NAME_OF_TRAVEL_PLAN` before deleting a friend.

Format: `delete -friend INDEX`

Example: `delete -friend 1`

## Edit

### 1. Editing a TravelPlan (G)

Edits an existing travel plan in the travel planner by its index.

Format: `edit -travelplan INDEX n/NAME sd/YYYY-MM-DD ed/YYYY-MM-DD​`

Example: `edit -travelplan 1 n/Paris sd/2020-10-11 ed/2020-10-15`

### 2. Editing an Activity (L)

Edits an existing activity in the the travel plan/wishlist in the current directory.

Format: `edit -activity INDEX n/NAME i/LEVEL_OF_IMPORTANCE l/LOCATION c/COST d/YYYY-MM-DD HH:mm`

Example: `edit -activity 3 n/Visit theme park i/5 l/Sensota c/80 d/2020-10-11 15:00`

#### 3. Editing an Accommodation (L)

Edits an existing accommodation in the travel plan in the current directory.
This command can only be used within a travel plan. Use `goto NAME_OF_TRAVEL_PLAN` before editing an accommodation.

Format: `edit -accommodation INDEX n/NAME l/LOCATION c/COST sd/YYYY-MM-DD ed/YYYY-MM-DD​`

Example: `edit -accommodation 3 n/The Hotel l/Bukit Timah c/60 sd/2020-10-11 ed/2020-10-15`

#### 4. Editing a Friend (L)

Edits an existing friend in the in the travel plan in the current directory.
This command can only be used within a travel plan. Use `goto NAME_OF_TRAVEL_PLAN` before editing a friend.

Format: `edit -friend INDEX n/NAME m/MOBILE_NUMBER p/PASSPORT_NUMBER​`

Example: `edit -friend 3 n/John m/91234567 p/E7654321`

--------------------------------------------------------------------------------------------------------------------

## FAQ [Coming soon]

--------------------------------------------------------------------------------------------------------------------
