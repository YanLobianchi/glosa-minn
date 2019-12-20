# usage example: glosaMaxx.py 10-2019
import requests
import re
import csv
import sys
import os


site = 'http://172.22.1.108:8080/'
competencia = ''
if sys.argv:
	competencia = competencia + sys.argv[1]

if not os.path.exists(competencia):
    os.makedirs(competencia)


def acessarPagina(pagina):
	r = requests.get(pagina)
	if r.status_code == 200:
		print("Pagina: " + pagina + " aberta com sucesso")
	else:
		print("Erro ao abrir pagina " + pagina)
	return r

def salvarArquivo(nomeArquivo, conteudo):
	caminho = competencia + '/' + nomeArquivo
	with open(caminho, 'w') as f:
	    writer = csv.writer(f)
	    for line in conteudo.iter_lines():
	        writer.writerow(line.decode('utf-8').split(','))

	print("Arquivo salvo em {}".format(caminho))



#abrindo pagina inicial do convenio
pag_ini = 'pagamento-glosamax/'
r = acessarPagina(site + pag_ini)

#coletando proxima pagina
conteudo = r.text
conteudo = conteudo.replace('\n', '')
print("Site encontrado: " + re.findall('Controladores.*href="(.*)">.*<\/a>', conteudo)[0])


#acessando listagem de paginas
pag_arquivos = re.findall('Controladores.*href="(.*)">.*<\/a>', conteudo)[0]
r = acessarPagina(site + pag_arquivos)

#coletando links das paginas
conteudo = r.text
conteudo = conteudo.replace('\n', '')
paginas = re.findall('<a href="([^>]*)">P.gina', conteudo)
print("{} paginas extraidas".format(len(paginas)))

#acessando paginas dos arquivos e baixando os arquivos
links = []
for pagina in paginas:

	r = acessarPagina(site + pagina)
	conteudo = r.text
	conteudo = conteudo.replace('\n', '')
	pag = re.findall('<a[^<]*<\/a>', conteudo)
	pag = [re.findall('href="(.*)"\s*target.*">(.*)<\/a>', p)[0] for p in pag if "download" in p]
	print("{} links encontrados na pagina".format(len(pag)))
	print(pag)

	# baixando arquivos e salvando
	for link in pag:
		print(link)
		r = acessarPagina(link[0])
		salvarArquivo(link[1], r)



