def establishTable(conn):
    qs = ('Drop table airports')
    executeSQL(conn, qs)
    conn.commit()

    qs = ( 'Create table airports(id INT,long_name VARCHAR(30),short_name VARCHAR(5));')
    executeSQL(conn, qs)

def generateEntries(conn):
    fh = open('/media/lbontecou/3890-3D81/csc4350_software_engineering/travlr/db_generator/airports.txt').read()
    i = 1
    for line in fh.split('\n'):
        if line:
            line = line.split('|')
            long = line[0].replace(',','').strip()
            short = line[1].strip()
            qs = ("insert into airports(id, long_name, short_name) "+
                    "VALUES ("+str(i)+", '"+long+"', '"+short+"');")
            executeSQL(conn,qs)
        i+=1
    conn.commit()


def executeSQL(conn, query_string):
    try:
        cursor = conn.cursor()
        cursor.execute(query_string)
    except Exception as e:
        print "Error: "+str(e)
    return cursor
