#!/usr/bin/python

import sqlite3
import flight_generator, airports_generator

db_path = "../db/travlr.db"

def main():
    db_conn = sqlite3.connect(db_path)

    airports_generator.establishTable(db_conn)
    airports_generator.generateEntries(db_conn)

    flight_generator.establishTable(db_conn)
    flight_generator.generateEntries(db_conn)

main()
