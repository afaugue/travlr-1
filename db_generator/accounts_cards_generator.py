def establishTable(conn):
    qs = ('drop table accounts_cards;')
    executeSQL(conn, qs)
    conn.commit()

    qs = (
        '''Create table accounts_cards
          (id INT,
           account_id INT,
           card_id INT);'''
    )
    executeSQL(conn, qs)

def generateEntries(conn):
    qs = ('insert into accounts_cards '
          '(id, account_id, card_id) VALUES '
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
