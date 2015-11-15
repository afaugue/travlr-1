def establishTable(conn):
    qs = ('drop table accounts_bookings;')
    executeSQL(conn, qs)
    conn.commit()

    qs = (
        '''Create table accounts_bookings
          (id INTEGER PRIMARY KEY   AUTOINCREMENT,
           account_id INT,
           booking_id INT);'''
    )
    executeSQL(conn, qs)

def generateEntries(conn):
    qs = ('insert into accounts_bookings '
          '(id, account_id, booking_id) VALUES '
          '( 1, 1, 1);')
    executeSQL(conn, qs)
    conn.commit()

def executeSQL(conn, query_string):
    try:
        cursor = conn.cursor()
        cursor.execute(query_string)
    except Exception as e:
        print query_string
        print "Error: "+str(e)

    return cursor
