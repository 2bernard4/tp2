# tp2
## 1
Cada Thread creado al extender la clase Thread crea un objeto único para él y se asocia con ese objeto.
Por otro lado, cada subproceso creado al implementar una interfaz runnable comparte la misma instancia runnable.

Como cada thread está asociado con un objeto único cuando se crea al extender la clase Thread, se requiere más memoria. 
Por otro lado, cada subproceso creado alimplementar la interfaz Runnable comparte el mismo espacio de objeto, por lo tanto,
requiere menos memoria.

Si extiende la clase Thread más allá, puede heredar cualquier otra clase ya que Java no permite la herencia múltiple, 
mientras que la implementación de Runnable aún ofrece la posibilidad de que una clase herede cualquier otra clase.

Uno debe extender una clase Thread solo si tiene que anular o especializar algunos otros métodos de la clase Thread. 
Debe implementar una interfaz Runnable si solo desea especializar el método de ejecución.

La extensión de la clase Thread introduce un acoplamiento estricto en el código, ya que el código de Thread y el trabajo de thread 
están contenidos en la misma clase. Por otro lado, la interfaz de ejecución runnable introduce un acoplamiento suelto en el código,
ya que el código del subproceso está separado del trabajo asignado al subproceso.

## 2
Los estados del hilo de java son los siguientes:
 
New/Runnable/Running/Non-Runnable (Blocked)/Terminated

## 3
start (): se usa para crear una pila de llamadas separada para el hilo. Se crea una pila de llamadas por separado y, a continuación,
JVM llama a run ().

yield():significa que el Thread no está haciendo nada particularmente importante y, si es necesario ejecutar otros threads o 
procesos, deberían ejecutarse. De lo contrario, el thread actual continuará ejecutándose.

sleep (): este método hace que el thread que se está ejecutando se duerma durante la cantidad especificada de milisegundos.

join(): se usa para unir el inicio de la ejecución de un thread al final de la ejecución de otro thread, de manera que un thread
no comienza a ejecutarse hasta que otro thread finaliza.
Si se llama a join () en una instancia de Thread, el hilo actualmente en ejecución se bloqueará hasta que la instancia de 
Thread haya terminado de ejecutarse
