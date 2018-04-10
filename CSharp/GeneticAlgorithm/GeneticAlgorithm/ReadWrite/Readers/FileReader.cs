using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace GeneticAlgorithm
{
    public class FileReader : IReader
    {
        //----------------------------------------------------
        //Поля
        //----------------------------------------------------
       
        //Файл
        private StreamReader file;

        //----------------------------------------------------
        //Методы
        //----------------------------------------------------

        /// <summary>
        /// Считывает строку из файла file
        /// </summary>
        /// <returns>возвращает считанную строку</returns>
        public string ReadLine()
        {
            return file.ReadLine();
        }

        /// <summary>
        /// закрывает файл
        /// </summary>
        public void Clear()
        {
            file.Close();
        }

        //----------------------------------------------------
        //Конструкторы
        //----------------------------------------------------

        public FileReader(string FilePath)
        {
            file = new StreamReader(FilePath);
        }
    }
}