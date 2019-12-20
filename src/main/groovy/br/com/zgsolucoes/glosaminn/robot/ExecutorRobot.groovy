package br.com.zgsolucoes.glosaminn.robot

import org.grails.io.support.ClassPathResource

class ExecutorRobot {



	static void executar(){
		String path = "src/main/resources/robot/glosaMaxx/glosaMaxx.py"
		String comando = 'python3 ' + path + '/glosaMaxx.py ' + path + '/12-2019'
		println comando.execute().text
	}
}

