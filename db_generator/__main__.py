#!/usr/bin/python

import sqlite3, os
import flight_generator, airports_generator, accounts_generator, card_generator
import accounts_cards_generator

dir = os.path.dirname(__file__)
db_path = dir+'/../db/travlr.db'
print db_path

def main():
    db_conn = sqlite3.connect(db_path)

    print "Generating Airport Table"
    airports_generator.establishTable(db_conn)
    airports_generator.generateEntries(db_conn)

    print "Generating Flights Table"
    #flight_generator.establishTable(db_conn)
    #flight_generator.generateEntries(db_conn)

    print "Generating Accounts Table"
    accounts_generator.establishTable(db_conn)
    accounts_generator.generateEntries(db_conn)

    print "Generating Credit Card Table"
    card_generator.establishTable(db_conn)
    card_generator.generateEntries(db_conn)

    print "Card - Account fk"
    accounts_cards_generator.establishTable(db_conn)
    accounts_cards_generator.generateEntries(db_conn)


main()
