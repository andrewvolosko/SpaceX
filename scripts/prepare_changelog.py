import os


def read_changelog():
    inputfile = open(os.getcwd() + '/doc/CHANGELOG.md')
    outputfile = open('changelog.txt', 'w')

    for i in range(5): inputfile.next()
    for line in inputfile:
        if not line.strip():
            break
        outputfile.writelines(line)

    inputfile.close()


read_changelog()