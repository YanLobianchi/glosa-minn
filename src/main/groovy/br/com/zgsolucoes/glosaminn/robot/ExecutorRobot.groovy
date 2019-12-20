package br.com.zgsolucoes.glosaminn.robot

class ExecutorRobot {

	static void executar() {
		String path = "src/main/resources/robot/glosaMaxx"
		String comando = 'python3 ' + path + '/glosaMaxx.py ' + path + '/12-2019'
		println comando.execute().text
	}
}

