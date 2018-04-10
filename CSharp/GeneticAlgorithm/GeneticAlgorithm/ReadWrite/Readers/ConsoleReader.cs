using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    class ConsoleReader: IReader
    {
        //----------------------------------------------------
        //Методы
        //----------------------------------------------------
        /// <summary>
        /// Считывает строку с консоли
        /// </summary>
        /// <returns>возвращает считанную строку</returns>
        public string ReadLine()
        {
            return Console.ReadLine();
        }


        /// <summary>
        /// В случае консольного чтения это не обяззательно
        /// </summary>
        public void Clear()
        {

        }
    }
}
